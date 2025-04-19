package com.parent.web.rest.controller;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

public class RetryableRestTemplate {

  private RestTemplate clientRestTemplate;
  private DiscoveryClient discoveryClient;

  public RetryableRestTemplate(RestTemplate clientRestTemplate,
      DiscoveryClient discoveryClient) {
    this.clientRestTemplate = clientRestTemplate;
    this.discoveryClient = discoveryClient;
  }

  @Retryable(maxAttemptsExpression = "3",
      value = ResourceAccessException.class,
      backoff = @Backoff(
          delayExpression = "1000",
          maxDelayExpression = "10000",
          multiplierExpression = "1")
      )
  public <T> ResponseEntity<T> exchange(String service, String url, HttpMethod method, HttpEntity<?> requestEntity,
      Class<T> responseType, Object... uriVariables) {
    return clientRestTemplate.exchange(url, method, requestEntity, responseType, uriVariables);
  }

  @Recover
  public <T> ResponseEntity<T> recoverExchange(Exception exception, String service, String url, HttpMethod method,
      HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables) throws Exception {
     //  some code here
    throw exception;
  }
}
