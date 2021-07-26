package com.qinxiu.jwtlogin.configuration;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.rsa.crypto.RsaSecretEncryptor;

@Configuration
public class JwtAuthConfig {

  public static int expirationMs;

  @Value("${jwtAuth.config.expirationMs}")
  private int expirationMsValue;

  @PostConstruct
  public void initialize() {
    expirationMs = this.expirationMsValue;
  }

  /**
   * RSA 加密解密bean.
   *
   * @return {@link TextEncryptor}
   */
  @Bean
  public TextEncryptor rsaEncryptor() {
    return new RsaSecretEncryptor();
  }

}
