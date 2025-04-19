package com.parent.web.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

import java.net.URLEncoder;

@Component
public class RestClient {

  private final RetryableRestTemplate retryableRestTemplate;

  @Autowired
  public RestClient(RetryableRestTemplate retryableRestTemplate) {
    this.retryableRestTemplate = retryableRestTemplate;
  }

  public <T> ResponseEntity<T> invoke(String service, HttpMethod method) {
    try {
      return retryableRestTemplate.exchange(null, URLEncoder.encode("test.com"), method, null,
          null);
    } catch (HttpStatusCodeException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
      return null;
  }
}
