package com.qinxiu.jwtlogin.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;
import com.qinxiu.jwtlogin.helper.customeException.ResponseResult;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

  private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);


  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException, ServletException {
    logger.error("Unauthorized error: {}", authException.getMessage());
    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
//    response.setStatus(HttpStatus.UNAUTHORIZED.value());
//    ResponseResult<Void> responseData = ResponseResult.<Void>builder().code(HttpStatus.UNAUTHORIZED.value())
//        .message(HttpStatus.UNAUTHORIZED.getReasonPhrase()).build();
//
//    response.getOutputStream().println(new GsonBuilder().create().toJson(responseData));
  }

}
