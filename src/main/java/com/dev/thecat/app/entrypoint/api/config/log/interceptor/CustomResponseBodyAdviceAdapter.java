package com.dev.thecat.app.entrypoint.api.config.log.interceptor;

import com.dev.thecat.app.entrypoint.api.config.log.LoggingService;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class CustomResponseBodyAdviceAdapter implements ResponseBodyAdvice<Object> {

  private final LoggingService loggingService;

  public CustomResponseBodyAdviceAdapter(
      final LoggingService loggingService) {
    this.loggingService = loggingService;
  }

  @Override
  public boolean supports(MethodParameter methodParameter,
                          Class<? extends HttpMessageConverter<?>> classA
  ) {
    return true;
  }

  @Override
  public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
                                Class<? extends HttpMessageConverter<?>> classA,
                                ServerHttpRequest serverHttpRequest,
                                ServerHttpResponse serverHttpResponse) {
    if (serverHttpRequest instanceof ServletServerHttpRequest
        && serverHttpResponse instanceof ServletServerHttpResponse) {
      this.loggingService
          .logResponse(((ServletServerHttpRequest) serverHttpRequest).getServletRequest(),
              ((ServletServerHttpResponse) serverHttpResponse).getServletResponse(), o);
    }

    return o;
  }
}
