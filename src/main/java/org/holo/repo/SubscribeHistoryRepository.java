package org.holo.repo;

import org.holo.entity.SubscribeHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscribeHistoryRepository extends MongoRepository<SubscribeHistory, String> {

  List<SubscribeHistory> findByUserId(String userId);

  void removeByUserId(String userId);

  List<SubscribeHistory> queryFirstBySubIdAndUserId(Integer subId, String userId);

   void   removeFirstBySubIdAndUserId(Integer subId, String userId);
}
