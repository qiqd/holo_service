package org.holo.service;

import lombok.RequiredArgsConstructor;
import org.holo.content.UserContent;
import org.holo.entity.PlaybackHistory;
import org.holo.repo.PlaybackHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaybackHistoryService {
  private final PlaybackHistoryRepository playbackHistoryRepository;
  private final UserContent userContent;

  public List<PlaybackHistory> queryAll() {
    return playbackHistoryRepository.findByUserId(userContent.getUserId());
  }

  public List<PlaybackHistory> saveAll(List<PlaybackHistory> playbackHistories) {
    playbackHistories.forEach(playbackHistory -> playbackHistory.setUserId(userContent.getUserId()));
    playbackHistoryRepository.saveAll(playbackHistories);

    return playbackHistoryRepository.saveAll(playbackHistories);
  }

  public List<PlaybackHistory> removeAll() {
    return playbackHistoryRepository.removeByUserId(userContent.getUserId());
  }
}
