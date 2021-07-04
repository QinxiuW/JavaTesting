package com.qinxiu.jwtlogin.filter;


import com.qinxiu.jwtlogin.dto.JwtPayloadDto;
import com.qinxiu.jwtlogin.helper.JwtAuthHelper;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtTokenFilter extends OncePerRequestFilter {

  @Value("${jwtAuth.config.secret}")
  private String secret;

  @Autowired
  private com.qinxiu.jwtlogin.configuration.CostumeUserDetailsService CostumeUserDetailsService;

  private static final Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    try{

      String accessToken = parseJwt(request); //get jwt token from request
      if (accessToken != null && JwtAuthHelper.verifyToken(accessToken,secret)){
        JwtPayloadDto payload = JwtAuthHelper.getPayload(accessToken);
        // can be email/id/name
        UserDetails userDetails = CostumeUserDetailsService.loadUserById(payload.getUserId());
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        // set the authentication into securityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }else{
        logger.error("Invalid token");
      }
    } catch(Exception e){
      logger.error("Cannot set user authentication: {}", e);
    }
  }


  private String parseJwt(HttpServletRequest request) {
    String headerAuth = request.getHeader("Authorization");

    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
      return headerAuth.substring(7, headerAuth.length());
    }

    return null;
  }

}
