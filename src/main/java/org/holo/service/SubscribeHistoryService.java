package org.holo.service;

import lombok.RequiredArgsConstructor;
import org.holo.content.UserContent;
import org.holo.entity.SubscribeHistory;
import org.holo.repo.SubscribeHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscribeHistoryService {
  private final SubscribeHistoryRepository repository;
  private final UserContent userContent;


  public List<SubscribeHistory> queryAll() {
    return repository.findByUserId(userContent.getUserId());
  }

  public SubscribeHistory queryBySubId(Integer subId) {
    return repository.queryFirstBySubIdAndUserId(subId, userContent.getUserId()).getFirst();
  }

  public void removeBySubId(Integer subId) {
    repository.removeFirstBySubIdAndUserId(subId, userContent.getUserId());
  }

  public SubscribeHistory save(SubscribeHistory subscribeHistory) {
    subscribeHistory.setUserId(userContent.getUserId());
    subscribeHistory.setCreatedAt(LocalDateTime.now());
    List<SubscribeHistory> subscribeHistories = repository.queryFirstBySubIdAndUserId(subscribeHistory.getSubId(), userContent.getUserId());
    if (subscribeHistories.isEmpty()) {
      return repository.save(subscribeHistory);
    } else {
      SubscribeHistory first = subscribeHistories.getFirst();
      first.setIsSync(true);
      first.setAirDate(subscribeHistory.getAirDate());
      first.setTitle(subscribeHistory.getTitle());
      first.setImgUrl(subscribeHistory.getImgUrl());
      return repository.save(first);
    }
  }

  public void removeAll() {
    repository.removeByUserId(userContent.getUserId());
  }
}
