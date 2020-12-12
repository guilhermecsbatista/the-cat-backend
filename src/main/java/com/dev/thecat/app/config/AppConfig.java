package com.dev.thecat.app.config;

import java.util.concurrent.Executor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
@EnableScheduling
public class AppConfig {

  @Value("${application.thread.core.pool.size}")
  private Integer corePoolSize;

  @Value("${application.thread.max.pool.size}")
  private Integer maxPoolSize;

  @Value("${application.thread.queue.capacity}")
  private Integer queueCapacity;


  @Bean("threadPool")
  public Executor asyncExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(corePoolSize);
    executor.setMaxPoolSize(maxPoolSize);
    executor.setQueueCapacity(queueCapacity);
    executor.setThreadNamePrefix("thread-");
    executor.initialize();
    return executor;
  }
}
