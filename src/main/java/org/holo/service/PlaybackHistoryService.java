package org.holo.service;

import lombok.RequiredArgsConstructor;
import org.holo.content.UserContent;
import org.holo.entity.PlaybackHistory;
import org.holo.repo.PlaybackHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaybackHistoryService {
  private final PlaybackHistoryRepository repository;
  private final UserContent userContent;


  public List<PlaybackHistory> queryAll() {
    return repository.findByUserId(userContent.getUserId());
  }

  public PlaybackHistory queryBySubId(Integer subId) {
    return repository.queryFirstBySubIdAndUserId(subId, userContent.getUserId()).getFirst();
  }
  public void removeBySubId(Integer subId) {
    repository.removeFirstBySubIdAndUserId(subId, userContent.getUserId());
  }
  public PlaybackHistory save(PlaybackHistory playbackHistory){
    playbackHistory.setUserId(userContent.getUserId());
    playbackHistory.setCreatedAt(LocalDateTime.now());
    List<PlaybackHistory> playbackHistories = repository.queryFirstBySubIdAndUserId(playbackHistory.getSubId(), userContent.getUserId());
    if (playbackHistories.isEmpty()) {
       playbackHistory.setIsSync(true);
       repository.save(playbackHistory);
    }else {
      PlaybackHistory first = playbackHistories.getFirst();
      first.setIsSync(true);
      first.setLastPlaybackAt(playbackHistory.getLastPlaybackAt());
      first.setPosition(playbackHistory.getPosition());
      first.setEpisodeIndex(playbackHistory.getEpisodeIndex());
      first.setLineIndex(playbackHistory.getLineIndex());
      first.setAirDate(playbackHistory.getAirDate());
      first.setTitle(playbackHistory.getTitle());
      first.setImgUrl(playbackHistory.getImgUrl());
      repository.save(first);
    }

    return repository.save(playbackHistory);
  }

  public void removeAll() {
    repository.removeByUserId(userContent.getUserId());
  }
}
