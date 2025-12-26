package org.holo.controller;

import lombok.RequiredArgsConstructor;
import org.holo.entity.History;
import org.holo.service.HistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class HistoryController {
  private final HistoryService historyService;

  @GetMapping("/query")
  public ResponseEntity<List<History>> queryHistory() {
    return ResponseEntity.ok(historyService.queryHistory());
  }

  @PostMapping("/save")
  public ResponseEntity<List<History>> saveHistory(@RequestBody History history) {
    historyService.saveHistory(history);
    return ResponseEntity.ok(historyService.queryHistory());
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteHistory(@PathVariable String id) {
    historyService.deleteHistoryById(id);
    return ResponseEntity.ok("History deleted");
  }

  @DeleteMapping("/delete-all")
  public ResponseEntity<String> removeAllHistory() {
    historyService.removeAllHistory();
    return ResponseEntity.ok("All history removed");
  }
}
