package org.holo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.holo.entity.PlaybackHistory;
import org.holo.entity.SubscribeHistory;
import org.holo.service.PlaybackHistoryService;
import org.holo.service.SubscribeHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
@Tag(name = "History Management", description = "历史记录管理接口 - 包括播放历史和订阅历史")
public class HistoryController {
  private final SubscribeHistoryService subscribeHistoryService;
  private final PlaybackHistoryService playbackHistoryService;

  @Operation(summary = "查询订阅历史", description = "获取当前用户的所有订阅历史记录")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "查询成功",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = SubscribeHistory.class)))
  })
  @GetMapping("/query/subscribe")
  public ResponseEntity<List<SubscribeHistory>> querySubscribeHistory() {
    return ResponseEntity.ok(subscribeHistoryService.queryAll());
  }

  @Operation(summary = "查询播放历史", description = "获取当前用户的所有播放历史记录")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "查询成功",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = PlaybackHistory.class)))
  })
  @GetMapping("/query/playback")
  public ResponseEntity<List<PlaybackHistory>> queryPlaybackHistory() {
    return ResponseEntity.ok(playbackHistoryService.queryAll());
  }

  @Operation(summary = "保存订阅历史", description = "批量保存或更新订阅历史记录")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "保存成功",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = SubscribeHistory.class)))
  })
  @PostMapping("/save/subscribe")
  public ResponseEntity<List<SubscribeHistory>> saveSubscribe(@RequestBody List<SubscribeHistory> historyList) {
    return ResponseEntity.ok(subscribeHistoryService.saveAll(historyList));
  }

  @Operation(summary = "保存播放历史", description = "批量保存或更新播放历史记录")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "保存成功",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = PlaybackHistory.class)))
  })
  @PostMapping("/save/playback")
  public ResponseEntity<List<PlaybackHistory>> savePlayback(@RequestBody List<PlaybackHistory> historyList) {
    return ResponseEntity.ok(playbackHistoryService.saveAll(historyList));
  }

  @Operation(summary = "删除所有订阅历史", description = "删除当前用户的所有订阅历史记录")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "删除成功",
          content = @Content(mediaType = "text/plain",
              schema = @Schema(implementation = String.class)))
  })
  @DeleteMapping("/delete/subscribe")
  public ResponseEntity<String> removeAllSubscribeHistory() {
    subscribeHistoryService.removeAll();
    return ResponseEntity.ok("All subscribe history removed");
  }

  @Operation(summary = "删除所有播放历史", description = "删除当前用户的所有播放历史记录")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "删除成功",
          content = @Content(mediaType = "text/plain",
              schema = @Schema(implementation = String.class)))
  })
  @DeleteMapping("/delete/playback")
  public ResponseEntity<String> removeAllPlaybackHistory() {
    playbackHistoryService.removeAll();
    return ResponseEntity.ok("All playback history removed");
  }
}
