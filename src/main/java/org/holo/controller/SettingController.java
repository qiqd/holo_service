package org.holo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.holo.entity.AppSetting;
import org.holo.service.AppSettingHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/setting")
@RequiredArgsConstructor
@Tag(name = "设置", description = "设置管理接口")
public class SettingController {
  private final AppSettingHistoryService appSettingHistoryService;

  @GetMapping
  @Operation(summary = "查询应用设置")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "查询成功",
                  content = @Content(mediaType = "application/json",
                          schema = @Schema(implementation = AppSetting.class)))
  })
  public ResponseEntity<AppSetting> queryAppSetting() {
    AppSetting appSetting = appSettingHistoryService.queryAppSetting();
    System.out.println(appSetting);
    return ResponseEntity.ok(appSetting);
  }

  @PostMapping
  @Operation(summary = "保存应用设置")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "保存成功",
                  content = @Content(mediaType = "application/json",
                          schema = @Schema(implementation = AppSetting.class)))
  })
  public ResponseEntity<Void> saveAppSetting(@RequestBody AppSetting appSetting) {
    appSettingHistoryService.saveAppSetting(appSetting);
    return ResponseEntity.ok().build();
  }
}
