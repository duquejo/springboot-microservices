package com.duquejo.hotels.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {

  @Bean("restClient")
  public RestTemplate registerRestTemplate() {
    return new RestTemplate();
  }
}
