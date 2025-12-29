package org.holo.service;

import lombok.RequiredArgsConstructor;
import org.holo.content.UserContent;
import org.holo.entity.PlaybackHistory;
import org.holo.repo.PlaybackHistoryRepository;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaybackHistoryService {
  private final PlaybackHistoryRepository playbackHistoryRepository;
  private final UserContent userContent;
  private final MongoTemplate mongoTemplate;

  public List<PlaybackHistory> queryAll() {
    return playbackHistoryRepository.findByUserId(userContent.getUserId());
  }

  public List<PlaybackHistory> saveAll(List<PlaybackHistory> playbackHistories) {
    playbackHistories.forEach(playbackHistory -> {
      playbackHistory.setUserId(userContent.getUserId());
      playbackHistory.setCreatedAt(LocalDateTime.now());
    });
    BulkOperations bulkOps = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, PlaybackHistory.class);
    for (PlaybackHistory history : playbackHistories) {
      Query query = new Query(Criteria.where("subId").is(history.getSubId()).and("userId").is(userContent.getUserId()));
      Update update = new Update()
              .set("title", history.getTitle())
              .set("imgUrl", history.getImgUrl())
              .set("lastPlaybackAt", history.getLastPlaybackAt())
              .set("position", history.getPosition())
              .set("airDate", history.getAirDate())
              .set("createdAt", history.getCreatedAt())
              .set("isSync", history.getIsSync())
              .set("episodeIndex", history.getEpisodeIndex())
              .set("lineIndex", history.getLineIndex());
      bulkOps.upsert(query, update);
    }
    bulkOps.execute();
    return playbackHistories;
  }

  public List<PlaybackHistory> removeAll() {
    return playbackHistoryRepository.removeByUserId(userContent.getUserId());
  }
}
