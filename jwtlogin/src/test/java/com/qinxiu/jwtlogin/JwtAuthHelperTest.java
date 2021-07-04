package com.qinxiu.jwtlogin;


import com.qinxiu.jwtlogin.helper.JwtAuthHelper;
import com.qinxiu.jwtlogin.security.entity.JwtPayload;
import java.io.UnsupportedEncodingException;
import static org.junit.Assert.*;

import org.junit.Test;

public class JwtAuthHelperTest {


  private static final String secret = "secret";


  private static final int expirationMs = 1200000;

  @Test
  public void JWTtest() throws UnsupportedEncodingException {
    JwtPayload payloadDto = JwtPayload.builder().userId(2).role("ADMIN").build();
    String token = JwtAuthHelper.generateToken(payloadDto,secret,expirationMs);
    assertNotNull(token);

    assertTrue(JwtAuthHelper.verifyToken(token,secret));
    assertFalse(JwtAuthHelper.verifyToken(token,"bad secret"));

    JwtPayload paylaod = JwtAuthHelper.getPayload(token);
    System.out.print(paylaod);
  }
}
