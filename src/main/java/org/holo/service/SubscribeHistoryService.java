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
  private final SubscribeHistoryRepository repository;
  private final UserContent userContent;
  private final MongoTemplate mongoTemplate;

  public List<SubscribeHistory> queryAll() {
    return repository.findByUserId(userContent.getUserId());
  }

  public SubscribeHistory queryBySubId(Integer subId) {
    return repository.queryFirstBySubIdAndUserId(subId, userContent.getUserId()).getFirst();
  }
  public void removeBySubId(Integer subId) {
      repository.removeFirstBySubIdAndUserId(subId, userContent.getUserId());
  }
 public SubscribeHistory save(SubscribeHistory subscribeHistory){
    subscribeHistory.setUserId(userContent.getUserId());
    subscribeHistory.setCreatedAt(LocalDateTime.now());
    return repository.save(subscribeHistory);
 }
  public void removeAll() {
    repository.removeByUserId(userContent.getUserId());
  }
}
