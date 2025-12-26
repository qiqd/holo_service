package org.holo.content;

import org.springframework.stereotype.Component;

@Component
public class UserContent {
  private final ThreadLocal<String> userId = new ThreadLocal<>();

  public String getUserId() {
    return userId.get();
  }

  public void setUserId(String userId) {
    this.userId.set(userId);
  }

  public void removeUserId() {
    userId.remove();
  }
}
