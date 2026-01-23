package org.holo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "app_setting")
public class AppSetting {
  @Id
  private String id;
  private String userId;
  private String useSystemColor;
  private String themeMode;
  private String filterWord;
  private DanmakuOption danmakuOption;

  @Data
  static class DanmakuOption {
    private Integer fontSize = 16;
    private Integer fontWeight = 4;
    private String fontFamily;
    private Double area = 1.0;
    private Integer duration = 10;
    private Integer staticDuration = 5;
    private Double opacity = 1.0;
    private Boolean hideBottom = false;
    private Boolean hideScroll = false;
    private Boolean hideTop = false;
    private Boolean hideSpecial = false;
    private Double strokeWidth = 1.5;
    private Boolean massiveMode = false;
    private Boolean safeArea = true;
    private Double lineHeight = 1.6;
  }

}
