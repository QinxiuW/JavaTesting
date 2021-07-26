package com.qinxiu.jwtlogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.qinxiu.jwtlogin.*")
public class JwtloginTestApplication {

  public static void main(String[] args) {
    SpringApplication.run(JwtloginTestApplication.class, args);

  }
}
