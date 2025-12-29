package org.holo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "account")
@Schema(description = "用户账户实体")
public class Account {
  @Id
  @Schema(description = "用户ID", example = "60f1b2b3e4b0a1d4e5f67890")
  private String id;

  @Indexed(background = true)
  @Schema(description = "用户名", example = "john_doe")
  private String username;

  @Schema(description = "用户密码", example = "password123")
  private String password;

  @Indexed(background = true)
  @Schema(description = "用户邮箱", example = "john@example.com")
  private String email;

  @Schema(description = "账户创建时间")
  private LocalDateTime createdAt;

  @Schema(description = "账户更新时间")
  private LocalDateTime updatedAt;
}
