package org.holo.service;

import lombok.RequiredArgsConstructor;
import org.holo.content.UserContent;
import org.holo.entity.Account;
import org.holo.repo.AccountRepository;
import org.holo.util.JwtUtil;
import org.holo.util.PasswordEncoderUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
  private final AccountRepository accountRepository;
  private final UserContent userContent;
  private final JwtUtil jwtUtil;
  private final PasswordEncoderUtil passwordEncoderUtil;

  public String loginAccount(Account account) {
    List<Account> accounts = accountRepository.findByEmail(account.getEmail());
    if (accounts.isEmpty()) {
      throw new IllegalArgumentException("Account not found");
    }
    Account foundAccount = accounts.getFirst();
    if (!passwordEncoderUtil.validatePassword(account.getPassword(), foundAccount.getPassword())) {
      throw new IllegalArgumentException("Invalid password");
    }
    return jwtUtil.generateToken(foundAccount.getId(), foundAccount.getUsername());
  }

  public String createAccount(Account account) {
    account.setId(null);
    account.setCreatedAt(LocalDateTime.now());
    account.setUpdatedAt(LocalDateTime.now());
    String encodedPassword = passwordEncoderUtil.encodePassword(account.getPassword());
    account.setPassword(encodedPassword);
    accountRepository.save(account);
    return jwtUtil.generateToken(account.getId(), account.getUsername());
  }

  public void updateAccount(Account account) {

    accountRepository.findById(userContent.getUserId()).ifPresentOrElse(
            a -> {
              a.setUsername(account.getUsername());
              if (account.getPassword() != null && !account.getPassword().isEmpty()) {
                a.setPassword(passwordEncoderUtil.encodePassword(account.getPassword()));
              }
              a.setEmail(account.getEmail());
              a.setUpdatedAt(LocalDateTime.now());
              accountRepository.save(a);
            },
            () -> {
              throw new IllegalArgumentException("Account not found");
            }
    );
  }

  public void deleteAccount() {
    accountRepository.deleteById(userContent.getUserId());
  }
}
