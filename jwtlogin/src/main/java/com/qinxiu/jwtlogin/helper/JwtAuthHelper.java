package com.qinxiu.jwtlogin.helper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.qinxiu.jwtlogin.security.entity.JwtPayload;
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

  public static String generateToken(JwtPayload payload,String secret,Integer expirationMs){

    Algorithm algorithm = null;
    try {
      algorithm = Algorithm.HMAC256(secret);
    } catch (UnsupportedEncodingException e) {
       logger.error("jwt token generation error");
       return null;
    }
    return JWT.create()
        .withClaim("userId", payload.getUserId())
        .withClaim("role", payload.getRole())
        .withIssuedAt(new Date())
        .withExpiresAt(new Date((new Date()).getTime() + expirationMs))
        .sign(algorithm);
  }

  public static boolean verifyToken(String token,String secret) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      JWTVerifier verifier = JWT.require(algorithm).build();
      //verify signature & expDate
      DecodedJWT jwt = verifier.verify(token);
      return true;
    } catch (UnsupportedEncodingException | JWTVerificationException e) {
      return false;
    }
  }

  public static JwtPayload getPayload(String token) {
    DecodedJWT decodedJWT = JWT.decode(token);
    Integer userId = decodedJWT.getClaim("userId").asInt();
    String role = decodedJWT.getClaim("role").asString();
    return JwtPayload.builder().userId(userId).role(role).build();
  }

}
