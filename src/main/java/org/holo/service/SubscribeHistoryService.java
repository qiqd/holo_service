package org.holo.service;

import lombok.RequiredArgsConstructor;
import org.holo.content.UserContent;
import org.holo.entity.SubscribeHistory;
import org.holo.repo.SubscribeHistoryRepository;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

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

  public List<SubscribeHistory> saveAll(List<SubscribeHistory> subscribeHistories) {
    BulkOperations bulkOps = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, SubscribeHistory.class);
    for (SubscribeHistory history : subscribeHistories) {
      Query query = new Query(Criteria.where("subId").is(history.getSubId()).and("userId").is(userContent.getUserId()));
      Update update = new Update()
              .set("title", history.getTitle())
              .set("imgUrl", history.getImgUrl())
              .set("airDate", history.getAirDate())
              .set("createdAt", history.getCreatedAt())
              .set("isSync", history.getIsSync());
      bulkOps.upsert(query, update);
    }
    bulkOps.execute();
    return subscribeHistories;
  }

  public List<SubscribeHistory> removeAll() {
    return subscribeHistoryRepository.removeByUserId(userContent.getUserId());
  }
}
