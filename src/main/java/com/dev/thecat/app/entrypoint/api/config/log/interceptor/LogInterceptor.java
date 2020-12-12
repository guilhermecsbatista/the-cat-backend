package com.dev.thecat.app.entrypoint.api.config.log.interceptor;

import com.dev.thecat.app.entrypoint.api.config.log.LoggingService;
import javax.inject.Named;
import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Named
public class LogInterceptor implements HandlerInterceptor {

  private final LoggingService loggingService;

  public LogInterceptor(final LoggingService loggingService) {
    this.loggingService = loggingService;
  }

  @Override
  public boolean preHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler) {

    if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name())
        && request.getMethod().equals(HttpMethod.GET.name())) {
      this.loggingService.logRequest(request, null);
    }

    return true;
  }
}
