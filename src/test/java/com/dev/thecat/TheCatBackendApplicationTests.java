package com.dev.thecat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
class TheCatBackendApplicationTests {

  @Test
  public void main() {
    TheCatBackendApplication
        .main(new String[] {"--spring.config.location=classpath:application-test.yml"});
  }

}
