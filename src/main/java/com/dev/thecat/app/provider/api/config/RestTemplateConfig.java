package com.dev.thecat.app.provider.api.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
  @Value("${application.client.default.timeout}")
  private Integer restDefaultTimeout;

  @Bean
  public RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    HttpClient httpClient = HttpClients.createDefault();
    HttpComponentsClientHttpRequestFactory factory =
        new HttpComponentsClientHttpRequestFactory(httpClient);
    factory.setConnectTimeout(restDefaultTimeout);
    factory.setReadTimeout(restDefaultTimeout);
    restTemplate.setRequestFactory(factory);
    return restTemplate;
  }
}
