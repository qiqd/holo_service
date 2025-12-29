package org.holo.service;

import lombok.RequiredArgsConstructor;
import org.holo.content.UserContent;
import org.holo.entity.SubscribeHistory;
import org.holo.repo.SubscribeHistoryRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscribeHistoryService {
  private final SubscribeHistoryRepository subscribeHistoryRepository;
  private final UserContent userContent;
  private final MongoTemplate mongoTemplate;

  public List<SubscribeHistory> queryAll() {
    return subscribeHistoryRepository.findByUserId(userContent.getUserId());
  }

  public List<SubscribeHistory> saveAll(List<SubscribeHistory> histories) {
    removeAll();
    histories.forEach(history -> {
      history.setCreatedAt(LocalDateTime.now());
      history.setUserId(userContent.getUserId());
    });
    return subscribeHistoryRepository.saveAll(histories);
  }

  public void removeAll() {
    subscribeHistoryRepository.removeByUserId(userContent.getUserId());
  }
}
