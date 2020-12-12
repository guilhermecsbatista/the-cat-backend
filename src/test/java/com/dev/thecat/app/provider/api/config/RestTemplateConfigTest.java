package com.dev.thecat.app.provider.api.config;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
public class RestTemplateConfigTest {
  private RestTemplateConfig restTemplateConfig;

  @Before
  public void setUp() {
    this.restTemplateConfig = new RestTemplateConfig();
    ReflectionTestUtils.setField(this.restTemplateConfig, "restDefaultTimeout", 5000);
  }

  @Test
  public void restTemplateTest() {
    this.restTemplateConfig.restTemplate();
  }

}
