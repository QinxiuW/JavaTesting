package com.qinxiu.jwtlogin;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.qinxiu.jwtlogin.dto.JwtPayloadDto;
import com.qinxiu.jwtlogin.helper.JwtAuthHelper;
import java.io.UnsupportedEncodingException;
import static org.junit.Assert.*;

import lombok.var;
import org.junit.Test;

public class JwtAuthHelperTest {


  private static final String secret = "secret";


  private static final int expirationMs = 1200000;

  @Test
  public void JWTtest() throws UnsupportedEncodingException {
    JwtPayloadDto payloadDto = JwtPayloadDto.builder().userId(2).role("ADMIN").build();
    String token = JwtAuthHelper.generateToken(payloadDto,secret,expirationMs);
    assertNotNull(token);

    assertTrue(JwtAuthHelper.verifyToken(token,secret));
    assertFalse(JwtAuthHelper.verifyToken(token,"bad secret"));

    JwtPayloadDto paylaod = JwtAuthHelper.getPayload(token);
    System.out.print(paylaod);
  }
}
