package org.holo.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.holo.content.UserContent;
import org.holo.util.JwtUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
  private final UserContent userContent;
  private final JwtUtil jwtUtil;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new HandlerInterceptor() {
              @Override
              public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                String requestHeader = request.getHeader("User-Agent");
                String token = request.getHeader("Authorization");
                if (!requestHeader.contains("Holo/client")) {
                  response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request header");
                  return false;
                }
                if (!jwtUtil.validateToken(token)) {
                  response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
                  return false;
                }
                String userId = jwtUtil.getUserIdFromToken(token);
                userContent.setUserId(userId);
                return true;
              }

              @Override
              public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
                userContent.removeUserId();
              }
            }).addPathPatterns("/**")
            .excludePathPatterns("/user/login", "/user/register");
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("*")
            .allowedHeaders("*");
  }
}
