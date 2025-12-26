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
@Document(collection = "history")
public class History {
  @Id
  private String id;
  private String userId;
  @Indexed(background = true)
  private String title;
  private LocalDateTime lastViewAt;
  private Integer position;
  private String imgUrl;
  private Boolean isLove;
  private Integer episodeIndex;
  private Integer lineIndex;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
