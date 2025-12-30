package org.holo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.holo.entity.SubscribeHistory;
import org.holo.service.SubscribeHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscribe")
@RequiredArgsConstructor
@Tag(name = "订阅记录", description = "订阅记录管理接口")
public class SubscribeController {
  private final SubscribeHistoryService subscribeHistoryService;

  @Operation(summary = "查询所有订阅历史", description = "获取当前用户的所有订阅历史记录")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "查询成功",
                  content = @Content(mediaType = "application/json",
                          schema = @Schema(implementation = SubscribeHistory.class)))
  })
  @GetMapping("/query/subscribe")
  public ResponseEntity<List<SubscribeHistory>> querySubscribeHistory() {
    return ResponseEntity.ok(subscribeHistoryService.queryAll());
  }

  @Operation(summary = "查询指定订阅历史", description = "根据订阅ID查询订阅历史记录")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "查询成功",
                  content = @Content(mediaType = "application/json",
                          schema = @Schema(implementation = SubscribeHistory.class)))
  })
  @GetMapping("/query/subscribe/{subId}")
  public ResponseEntity<SubscribeHistory> querySubscribeHistory(@PathVariable Integer subId) {
    return ResponseEntity.ok(subscribeHistoryService.queryBySubId(subId));
  }

  @Operation(summary = "删除指定订阅历史", description = "根据订阅ID删除订阅历史记录")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "删除成功",
                  content = @Content(mediaType = "text/plain",
                          schema = @Schema(implementation = String.class)))
  })
  @DeleteMapping("/query/subscribe/{subId}")
  public ResponseEntity<String> removeSubscribeHistory(@PathVariable Integer subId) {
    subscribeHistoryService.removeBySubId(subId);
    return ResponseEntity.ok("Subscribe history removed");
  }

  @Operation(summary = "保存订阅历史", description = "保存或更新订阅历史记录")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "保存成功",
                  content = @Content(mediaType = "application/json",
                          schema = @Schema(implementation = SubscribeHistory.class)))
  })
  @PostMapping("/save/subscribe")
  public ResponseEntity<SubscribeHistory> saveSubscribe(@RequestBody SubscribeHistory subscribeHistory) {
    return ResponseEntity.ok(subscribeHistoryService.save(subscribeHistory));
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

}
