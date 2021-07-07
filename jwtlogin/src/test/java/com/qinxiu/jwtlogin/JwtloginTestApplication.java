package com.qinxiu.jwtlogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.qinxiu.jwtlogin.config")
public class JwtloginTestApplication {
  public static void main(String[] args) {
    SpringApplication.run(JwtloginTestApplication.class, args);
  }
}
