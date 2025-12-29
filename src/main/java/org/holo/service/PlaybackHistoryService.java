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

  public List<PlaybackHistory> saveAll(List<PlaybackHistory> playbackHistories) {
    removeAll();
    playbackHistories.forEach(history -> {
      history.setCreatedAt(LocalDateTime.now());
      history.setUserId(userContent.getUserId());
    });
    return repository.saveAll(playbackHistories);
  }

  public void removeAll() {
    repository.removeByUserId(userContent.getUserId());
  }
}
