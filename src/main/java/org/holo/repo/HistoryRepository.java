package org.holo.repo;

import org.holo.entity.History;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends MongoRepository<History, String> {
  List<History> findAllByUserId(String userId);

  void deleteAllByUserId(String userId);

  void deleteByUserIdAndId(String userId, String id);
}
