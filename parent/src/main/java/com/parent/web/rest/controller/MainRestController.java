package com.parent.web.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;

@RestController
public class MainRestController {

  private RestClient restClient;

  @Autowired
  public MainRestController(RestClient restClient) {
    this.restClient = restClient;
  }

  @GetMapping(path = "/test")
  public ResponseEntity<?> createConfiguration() {

    return restClient.invoke("http://www.test.com", HttpMethod.POST);
  }
}
