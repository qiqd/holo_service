package org.holo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.holo.entity.PlaybackHistory;
import org.holo.service.PlaybackHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
@Tag(name = "History Management", description = "历史记录管理接口 - 包括播放历史和订阅历史")
public class HistoryController {

  private final PlaybackHistoryService playbackHistoryService;


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

  @GetMapping("/query/playback/{subId}")
  public ResponseEntity<PlaybackHistory> queryPlaybackHistory(@PathVariable Integer subId) {
    return ResponseEntity.ok(playbackHistoryService.queryBySubId(subId));
  }

  @Operation(summary = "保存播放历史", description = "批量保存或更新播放历史记录")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "保存成功",
                  content = @Content(mediaType = "application/json",
                          schema = @Schema(implementation = PlaybackHistory.class)))
  })
  @PostMapping("/save/playback")
  public ResponseEntity<PlaybackHistory> savePlayback(@RequestBody PlaybackHistory history) {
    return ResponseEntity.ok(playbackHistoryService.save(history));
  }

  @DeleteMapping("/delete/playback/{subId}")
  public ResponseEntity<String> removePlaybackHistory(@PathVariable Integer subId) {
    playbackHistoryService.removeBySubId(subId);
    return ResponseEntity.ok("Playback history removed");
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
