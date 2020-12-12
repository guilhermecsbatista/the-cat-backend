package com.dev.thecat.app.entrypoint.api.config.log.interceptor;

import static org.mockito.Mockito.mock;


import com.dev.thecat.app.entrypoint.api.config.log.LoggingService;
import javax.servlet.http.HttpServletRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomResponseBodyAdviceAdapterTest {
  private CustomResponseBodyAdviceAdapter customResponseBodyAdviceAdapter;

  @Mock
  private LoggingService loggingService;

  @Mock
  private HttpServletRequest httpServletRequest;

  @Before
  public void seUp() {
    this.customResponseBodyAdviceAdapter =
        new CustomResponseBodyAdviceAdapter(this.loggingService);
  }

  @Test
  public void supportsTest() {
    this.customResponseBodyAdviceAdapter.supports(null, null);
  }

  @Test
  public void afterBodyReadTest() {
    ServletServerHttpRequest request = mock(ServletServerHttpRequest.class);
    ServerHttpResponse response = mock(ServletServerHttpResponse.class);

    this.customResponseBodyAdviceAdapter
        .beforeBodyWrite(new Object(), null, MediaType.APPLICATION_JSON, null, request, response);
  }
}
