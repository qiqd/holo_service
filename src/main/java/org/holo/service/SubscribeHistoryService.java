package org.holo.service;

import lombok.RequiredArgsConstructor;
import org.holo.content.UserContent;
import org.holo.entity.SubscribeHistory;
import org.holo.repo.SubscribeHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscribeHistoryService {
  private final SubscribeHistoryRepository subscribeHistoryRepository;
  private final UserContent userContent;

  public List<SubscribeHistory> queryAll() {
    return subscribeHistoryRepository.findByUserId(userContent.getUserId());
  }
  public List<SubscribeHistory> saveAll(List<SubscribeHistory> subscribeHistories) {
    subscribeHistories.forEach(subscribeHistory -> subscribeHistory.setUserId(userContent.getUserId()));
    return subscribeHistoryRepository.saveAll(subscribeHistories);
  }
  public List<SubscribeHistory> removeAll() {
    return subscribeHistoryRepository.removeByUserId(userContent.getUserId());
  }
}
