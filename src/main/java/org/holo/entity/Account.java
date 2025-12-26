package org.holo.entity;

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
public class Account {
  @Id
  private String id;
  @Indexed(background = true)
  private  String username;
  private String password;
  @Indexed(background = true)
  private String email;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
