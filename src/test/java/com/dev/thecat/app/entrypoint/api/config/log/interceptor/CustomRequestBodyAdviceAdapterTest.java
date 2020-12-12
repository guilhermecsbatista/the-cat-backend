package com.dev.thecat.app.entrypoint.api.config.log.interceptor;

import com.dev.thecat.app.entrypoint.api.config.log.LoggingService;
import javax.servlet.http.HttpServletRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomRequestBodyAdviceAdapterTest {
  private CustomRequestBodyAdviceAdapter customRequestBodyAdviceAdapter;

  @Mock
  private LoggingService loggingService;

  @Mock
  private HttpServletRequest httpServletRequest;

  @Before
  public void seUp() {
    this.customRequestBodyAdviceAdapter =
        new CustomRequestBodyAdviceAdapter(this.loggingService, this.httpServletRequest);
  }

  @Test
  public void supportsTest() {
    this.customRequestBodyAdviceAdapter.supports(null, null, null);
  }

  @Test
  public void afterBodyReadTest() {
    this.customRequestBodyAdviceAdapter
        .afterBodyRead(new Object(), null, null,
            null, null);
  }
}
