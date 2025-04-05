package com.parent.web.rest.controller;

import com.logging.correlation.feign.InternalHeadersContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainRestController {

  @GetMapping(path = "/test")
  public ResponseEntity<?> createConfiguration() {

    String userName = (String) InternalHeadersContext.getInternalHeaders().getOrDefault("X-INTERNAL-USER-ID", "test_value");

    return new ResponseEntity<>(userName, HttpStatus.OK);
  }
}
