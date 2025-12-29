package org.holo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.holo.entity.Account;
import org.holo.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "用户账户管理", description = "用户账户相关的API接口，包括注册、登录、更新和删除功能")
public class AccountController {
  private final AccountService accountService;

  @PostMapping("/register")
  @Operation(summary = "用户注册", description = "创建新用户账户并返回JWT令牌")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "注册成功",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = String.class, example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."))),
      @ApiResponse(responseCode = "400", description = "注册失败",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = String.class, example = "错误信息")))
  })
  public ResponseEntity<String> createAccount(@RequestBody Account account) {
    return ResponseEntity.ok(accountService.createAccount(account));
  }

  @PutMapping("/update")
  @Operation(summary = "更新用户信息", description = "更新当前登录用户的账户信息")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "更新成功",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = String.class, example = "Account updated"))),
      @ApiResponse(responseCode = "400", description = "更新失败",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = String.class, example = "错误信息")))
  })
  public ResponseEntity<String> updateAccount(@RequestBody Account account) {
    accountService.updateAccount(account);
    return ResponseEntity.ok("Account updated");
  }

  @PostMapping("/login")
  @Operation(summary = "用户登录", description = "验证用户凭据并返回JWT令牌")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "登录成功",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = String.class, example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."))),
      @ApiResponse(responseCode = "400", description = "登录失败",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = String.class, example = "Invalid password")))
  })
  public ResponseEntity<String> loginAccount(@RequestBody Account account) {
    return ResponseEntity.ok(accountService.loginAccount(account));
  }

  @DeleteMapping("/delete")
  @Operation(summary = "删除用户账户", description = "删除当前登录用户的账户")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "删除成功",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = String.class, example = "Account deleted"))),
      @ApiResponse(responseCode = "400", description = "删除失败",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = String.class, example = "错误信息")))
  })
  public ResponseEntity<String> deleteAccount() {
    accountService.deleteAccount();
    return ResponseEntity.ok("Account deleted");
  }
}
