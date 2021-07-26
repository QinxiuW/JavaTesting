package com.qinxiu.jwtlogin;


import com.qinxiu.jwtlogin.configuration.JwtAuthConfig;
import com.qinxiu.jwtlogin.helper.JwtAuthHelper;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.encrypt.TextEncryptor;

@SpringBootTest(classes = JwtloginTestApplication.class)
public class Helpertest {


  @Resource
  TextEncryptor encryptor;

  @Test
  void helperTest() {

//    System.out.println(jwtAuthConfig.encryptor().encrypt("xxxxx"));
    System.out.println(JwtAuthConfig.expirationMs);
    System.out.println(encryptor.encrypt("xxxxx"));
  }
}
