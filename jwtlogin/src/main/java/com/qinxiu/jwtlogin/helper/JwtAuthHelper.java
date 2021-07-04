package com.qinxiu.jwtlogin.helper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.qinxiu.jwtlogin.dto.JwtPayloadDto;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JwtAuthHelper {

//  @Value("${jwtAuth.config.secret}")
//  private static String secret;
//
//  @Value("${jwtAuth.config.secret}")
//  private static int expirationMs;

  private static final Logger logger = LoggerFactory.getLogger(JwtAuthHelper.class);

  public static String generateToken(JwtPayloadDto payload,String secret,Integer expirationMs) throws UnsupportedEncodingException {

    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.create()
        .withClaim("userId", payload.getUserId())
        .withClaim("role", payload.getRole())
        .withClaim("exp", new Date((new Date()).getTime() + expirationMs))
        .withClaim("iat", new Date())
        .sign(algorithm);
  }

  public static boolean verifyToken(String token,String secret) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      JWTVerifier verifier = JWT.require(algorithm).build();
      DecodedJWT jwt = verifier.verify(token);
      return true;
    } catch (UnsupportedEncodingException | JWTVerificationException e) {
      return false;
    }
  }

  public static JwtPayloadDto getPayload(String token) {
    DecodedJWT decodedJWT = JWT.decode(token);
    Integer userId = decodedJWT.getClaim("userId").asInt();
    String role = decodedJWT.getClaim("role").asString();
    return JwtPayloadDto.builder().userId(userId).role(role).build();
  }


}
