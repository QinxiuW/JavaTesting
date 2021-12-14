package com.qinxiu.jwtlogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan("com.qinxiu.jwtlogin.*")
@EnableAsync
public class JwtloginTestApplication {

  public static void main(String[] args) {
    SpringApplication.run(JwtloginTestApplication.class, args);

  }
}
