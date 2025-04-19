package com.parent;

import com.parent.web.rest.controller.RetryableRestTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

    public Application(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    public static void main(String... args) {
    SpringApplication.run(Application.class, args);
  }

  private final DiscoveryClient discoveryClient;

  @Bean(name = "retryableRestTemplate")
  public RetryableRestTemplate retryableRestTemplate() {
    return new RetryableRestTemplate(new RestTemplate(), discoveryClient);
  }

}
