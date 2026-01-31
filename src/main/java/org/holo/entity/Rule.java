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
import java.util.Map;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "rule")
@Schema(description = "规则实体")
public class Rule {

  private static final String DEFAULT_USER_AGENT =
          "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/144.0.0.0 Safari/537.36 Edg/144.0.0.0";

  @Id
  @Schema(description = "规则 ID")
  private String id;

  @Indexed(background = true)
  @Schema(description = "用户 ID")
  private String userId;

  @Schema(description = "规则名称(通常为网站名称)")
  private String name;

  @Schema(description = "规则baseUrl(通常是网站的域名)")
  private String baseUrl;

  @Schema(description = "规则logoUrl(通常是网站的logo)")
  private String logoUrl;

  @Schema(description = "规则版本号")
  private String version;

  @Schema(description = "搜索Url(通常是网站的搜索页面)")
  private String searchUrl;

  @Schema(description = "搜索请求方法(默认get)")
  private String searchRequestMethod;

  @Schema(description = "搜索请求体(通常是网站的搜索页面的请求体)")
  private Map<String, String> searchRequestBody;

  @Schema(description = "搜索请求头部(通常是网站的搜索页面的请求头部)")
  private Map<String, String> searchRequestHeaders;

  @Schema(description = "是否是完整的搜索Url(如果是,则不与 baseUrl 拼接,否则拼接)")
  private Boolean fullSearchUrl;

  @Schema(description = "超时时间(默认5秒)")
  private Integer timeout;

  @Schema(description = "搜索选择器(通常是搜索结果的列表)")
  private String searchSelector;

  @Schema(description = "搜索图片选择器(通常是搜索结果的列表中的每一项的图片,一般是一个img标签)")
  private String itemImgSelector;

  @Schema(description = "是否是图片选择器中的src属性(如果是,则图片选择器中的src属性是图片的url,否则是data-original属性)")
  private Boolean itemImgFromSrc;

  @Schema(description = "搜索标题选择器(通常是搜索结果的列表中的每一项的标题,一般是一个a标签)")
  private String itemTitleSelector;

  @Schema(description = "MediaId选择器(通常是搜索结果的列表中的每一项的Id,一般是一个a标签)")
  private String itemIdSelector;

  @Schema(description = "搜索类型选择器(通常是搜索结果的列表中的每一项的内容的类型)")
  private String itemGenreSelector;

  @Schema(description = "详情Url(通常是网站的详情页面)")
  private String detailUrl;

  @Schema(description = "详情请求方法(默认get)")
  private String detailRequestMethod;

  @Schema(description = "详情请求体(通常是网站的详情页面的请求体)")
  private Map<String, String> detailRequestBody;

  @Schema(description = "详情请求头部(通常是网站的详情页面的请求头部)")
  private Map<String, String> detailRequestHeaders;

  @Schema(description = "是否是完整的详情Url(如果是,则不与 baseUrl 拼接,否则拼接)")
  private Boolean fullDetailUrl;

  @Schema(description = "路线选择器(该视频的播放路线)")
  private String lineSelector;

  @Schema(description = "剧集选择器(每一条线路下对应的剧集,一般是一个a标签)")
  private String episodeSelector;

  @Schema(description = "播放页面Url(通常是网站的播放页面的视频播放地址)")
  private String playerUrl;

  @Schema(description = "播放页面请求方法(默认get)")
  private String playerRequestMethod;

  @Schema(description = "播放页面请求体(通常是网站的播放页面的请求体)")
  private Map<String, String> playerRequestBody;

  @Schema(description = "播放页面请求头部(通常是网站的播放页面的请求头部)")
  private Map<String, String> playerRequestHeaders;

  @Schema(description = "是否是完整的播放Url(如果是 ,则不与 baseUrl 拼接,否则拼接)")
  private Boolean fullPlayerUrl;

  @Schema(description = "播放视频选择器(通常是播放页面的视频标签,比如video,iframe等)")
  private String playerVideoSelector;

  @Schema(description = "视频元素属性(通常是视频标签的src属性,比如video标签的src属性)")
  private String videoElementAttribute;

  @Schema(description = "嵌入视频选择器,英文逗号分隔(通常是播放页面的嵌入视频标签,比如iframe等)")
  private String embedVideoSelector;

  @Schema(description = "是否等待视频元素加载完成(如果是,则等待视频元素加载完成,否则立即返回)")
  private Boolean waitForMediaElement;

  @Schema(description = "视频url截取,通常是从params参数中截取视频url,比如params=videoUrl=xxxx,则截取xxxx,如果是null,则直接返回匹配的url")
  private String videoUrlSubsChar;

  @Schema(description = "规则更新时间")
  private LocalDateTime updateAt;

  @Schema(description = "规则是否启用")
  private Boolean isEnabled;

  @Schema(description = "是否是本地规则")
  private Boolean isLocal;
}
