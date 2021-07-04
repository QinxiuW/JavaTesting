package com.qinxiu.jwtlogin.controller;


import com.qinxiu.jwtlogin.dto.JwtPayloadDto;
import com.qinxiu.jwtlogin.helper.JwtAuthHelper;
import java.io.UnsupportedEncodingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Value("${jwtAuth.config.secret}")
  private  String secret;

  @Value("${jwtAuth.config.expirationMs}")
  private  int expirationMs;

  @PostMapping("/login")
  public String login() throws UnsupportedEncodingException {

    JwtPayloadDto payload = JwtPayloadDto.builder().userId(1).role("ADMIN").build();
    return JwtAuthHelper.generateToken(payload,secret,expirationMs);
  }

  @GetMapping("/ping")
  public String ping(){
    return "authController pong";
  }
}
