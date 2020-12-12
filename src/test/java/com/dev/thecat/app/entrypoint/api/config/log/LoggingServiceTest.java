package com.dev.thecat.app.entrypoint.api.config.log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class LoggingServiceTest {
  private LoggingService loggingService;

  @Before
  public void setUp() {
    this.loggingService = new LoggingService();
  }

  @Test
  public void logRequestTest() {
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setServerName("www.test.com");
    request.setRequestURI("/test");
    request.setQueryString("param1=value1&param");

    this.loggingService.logRequest(request, new Object());
  }

  @Test
  public void logRequestParamNullAndBodyNullTest() {
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setContentType("application/json");
    request.setServerName("www.test.com");
    request.setRequestURI("/test");
    request.setQueryString("param1=value1&param");
    request.setParameter("test", "test");

    this.loggingService.logRequest(request, null);
  }

  @Test
  public void logResponseTest() {
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setServerName("www.test.com");
    request.setRequestURI("/test");
    request.setQueryString("param1=value1&param");

    MockHttpServletResponse response = new MockHttpServletResponse();
    response.setHeader("test", "test");

    this.loggingService.logResponse(request, response, new Object());
  }
}
