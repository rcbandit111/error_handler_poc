package com.parent.web.rest.controller;

import com.security.audit.SpringSecurityAuditorAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainRestController {

  private SpringSecurityAuditorAware springSecurityAuditorAware;

  @Autowired
  public MainRestController(SpringSecurityAuditorAware springSecurityAuditorAware) {
    this.springSecurityAuditorAware = springSecurityAuditorAware;
  }

  @GetMapping(path = "/test")
  public ResponseEntity<?> createConfiguration() {

    String username = springSecurityAuditorAware.getCurrentAuditor().get();

    return new ResponseEntity<>(username, HttpStatus.OK);
  }
}
