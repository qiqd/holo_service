package org.holo.repo;

import org.holo.entity.PlaybackHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaybackHistoryRepository extends MongoRepository<PlaybackHistory, String> {

  List<PlaybackHistory> findByUserId(String userId);

  List<PlaybackHistory> removeByUserId(String userId);
}
