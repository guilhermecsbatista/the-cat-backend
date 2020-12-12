package com.dev.thecat.app.entrypoint.api.config.log;

import com.dev.thecat.app.entrypoint.api.config.log.interceptor.LogInterceptor;
import javax.inject.Named;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Named
public class WebConfig implements WebMvcConfigurer {

  private final LogInterceptor logInterceptor;

  public WebConfig(final LogInterceptor logInterceptor) {
    this.logInterceptor = logInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(this.logInterceptor);
  }
}
