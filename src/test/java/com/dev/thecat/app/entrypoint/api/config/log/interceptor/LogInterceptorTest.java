package com.dev.thecat.app.entrypoint.api.config.log.interceptor;

import com.dev.thecat.app.entrypoint.api.config.log.LoggingService;
import javax.servlet.DispatcherType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class LogInterceptorTest {
  private LogInterceptor logInterceptor;

  @Mock
  private LoggingService loggingService;

  @Before
  public void setUp() {
    this.logInterceptor = new LogInterceptor(this.loggingService);
  }

  @Test
  public void preHandleTest() {
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setServerName("www.test.com");
    request.setRequestURI("/test");
    request.setQueryString("param1=value1&param");
    request.setDispatcherType(DispatcherType.REQUEST);
    request.setMethod("GET");

    MockHttpServletResponse response = new MockHttpServletResponse();
    response.setHeader("test", "test");

    this.logInterceptor.preHandle(request, response, new Object());
  }

  @Test
  public void preHandleDispatcherForwardTest() {
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setServerName("www.test.com");
    request.setRequestURI("/test");
    request.setQueryString("param1=value1&param");
    request.setDispatcherType(DispatcherType.FORWARD);
    request.setMethod("GET");

    MockHttpServletResponse response = new MockHttpServletResponse();
    response.setHeader("test", "test");

    this.logInterceptor.preHandle(request, response, new Object());
  }

  public void preHandleMethodPutTypeTest() {
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setServerName("www.test.com");
    request.setRequestURI("/test");
    request.setQueryString("param1=value1&param");
    request.setDispatcherType(DispatcherType.REQUEST);
    request.setMethod("PUT");

    MockHttpServletResponse response = new MockHttpServletResponse();
    response.setHeader("test", "test");

    this.logInterceptor.preHandle(request, response, new Object());
  }
}
