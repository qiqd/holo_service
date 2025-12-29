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
    return repository.save(playbackHistory);
  }

  public void removeAll() {
    repository.removeByUserId(userContent.getUserId());
  }
}
