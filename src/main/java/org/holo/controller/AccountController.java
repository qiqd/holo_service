package org.holo.controller;

import lombok.RequiredArgsConstructor;
import org.holo.entity.Account;
import org.holo.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AccountController {
  private final AccountService accountService;

  @PostMapping("/register")
  public ResponseEntity<String> createAccount(@RequestBody Account account) {
    return ResponseEntity.ok(accountService.createAccount(account));
  }

  @PutMapping("/update")
  public ResponseEntity<String> updateAccount(@RequestBody Account account) {
    accountService.updateAccount(account);
    return ResponseEntity.ok("Account updated");
  }

  @PostMapping("/login")
  public ResponseEntity<String> loginAccount(@RequestBody Account account) {
    return ResponseEntity.ok(accountService.loginAccount(account));
  }

  @DeleteMapping("/delete")
  public ResponseEntity<String> deleteAccount() {
    accountService.deleteAccount();
    return ResponseEntity.ok("Account deleted");
  }
}
