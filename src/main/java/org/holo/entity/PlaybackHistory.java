package org.holo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "playback_history")
@Schema(description = "播放历史记录")
public class PlaybackHistory {
  @Schema(description = "记录ID,由mongodb自动生成", example = "64a1b2c3d4e5f6g7h8i9j0k1")
  @Id
  private String id;

  @Schema(description = "订阅ID", example = "123")
  @Indexed(background = true)
  private Integer subId;

  @Schema(description = "用户ID", example = "user123")
  @Indexed(background = true)
  private String userId;

  @Schema(description = "标题", example = "示例视频")
  private String title;

  @Schema(description = "图片URL", example = "https://example.com/image.jpg")
  private String imgUrl;

  @Schema(description = "最后播放时间", example = "2023-01-01T12:00:00")
  private LocalDateTime lastPlaybackAt;

  @Schema(description = "创建时间")
  private LocalDateTime createdAt;

  @Schema(description = "播放位置", example = "120")
  private Integer position;

  @Schema(description = "发布日期", example = "2023-01-01")
  private String airDate;

  @Schema(description = "集数索引", example = "1")
  private Integer episodeIndex;

  @Schema(description = "路线索引", example = "10")
  private Integer lineIndex;

  @Schema(description = "是否同步", example = "true")
  private Boolean isSync;
}
