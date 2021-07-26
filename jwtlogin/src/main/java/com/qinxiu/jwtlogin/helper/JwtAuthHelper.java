package com.qinxiu.jwtlogin.helper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.qinxiu.jwtlogin.security.entity.JwtPayload;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Slf4j
@Configuration
public class JwtAuthHelper {

  public static String generateToken(JwtPayload payload,String secret,Integer expirationMs){

    Algorithm algorithm = null;
    try {
      algorithm = Algorithm.HMAC256(secret);
    } catch (UnsupportedEncodingException e) {
       log.error("jwt token generation error");
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
