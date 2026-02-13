package org.holo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 应用设置实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("app_setting")
public class AppSetting {
    /**
     * 用户 id
     */
    @Id
    private String userId;
    /**
     * 弹幕设置
     */
    private DanmakuSetting danmakuSetting;

    /**
     * 使用系统颜色，只在Android 12+ 以上版本生效
     */
    private Boolean useSystemColor;

    /**
     * 主题模式
     * 0: 系统主题
     * 1: 浅色主题
     * 2: 深色主题
     */
    private Integer themeMode = 0;

    /**
     * 弹幕设置静态内部类
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DanmakuSetting {

        /**
         * 透明度 (默认: 1.0)
         */
        @Builder.Default
        private Double opacity = 1D;

        /**
         * 显示区域 (默认: 1.0)
         */
        @Builder.Default
        private Double area = 1D;

        /**
         * 字体大小 (默认: 16)
         */
        @Builder.Default
        private Double fontSize = 16D;

        /**
         * 隐藏顶部弹幕 (默认: false)
         */
        @Builder.Default
        private Boolean hideTop = false;

        /**
         * 隐藏滚动弹幕 (默认: false)
         */
        @Builder.Default
        private Boolean hideScroll = false;

        /**
         * 隐藏底部弹幕 (默认: false)
         */
        @Builder.Default
        private Boolean hideBottom = false;

        /**
         * 海量模式 (默认: false)
         */
        @Builder.Default
        private Boolean massiveMode = false;

        /**
         * 弹幕偏移量 (默认: 0)
         */
        @Builder.Default
        private Integer danmakuOffset = 0;

        /**
         * 过滤词，英文逗号分隔 (默认: "")
         */
        @Builder.Default
        private String filterWords = "";


    }

}