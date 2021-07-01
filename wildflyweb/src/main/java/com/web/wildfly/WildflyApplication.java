package com.web.wildfly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class WildflyApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(WildflyApplication.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(
      SpringApplicationBuilder builder) {
    return builder.sources(WildflyApplication.class);
  }

}
