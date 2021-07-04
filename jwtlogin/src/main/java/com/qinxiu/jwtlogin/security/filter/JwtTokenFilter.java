package com.qinxiu.jwtlogin.security.filter;


import com.qinxiu.jwtlogin.helper.customeException.BusinessException;
import com.qinxiu.jwtlogin.helper.customeException.BusinessStatus;
import com.qinxiu.jwtlogin.security.entity.JwtPayload;
import com.qinxiu.jwtlogin.helper.JwtAuthHelper;
import com.qinxiu.jwtlogin.security.services.ICostumeUserDetailsService;
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
  private ICostumeUserDetailsService CostumeUserDetailsService;

  private static final Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    try{

      String accessToken = parseJwt(request); //get jwt token from request
      if (accessToken != null ){

        if (!JwtAuthHelper.verifyToken(accessToken,secret)){
          throw new BusinessException(BusinessStatus.AUTH_INVALID_TOKEN);
        }

        // get payload info
        JwtPayload payload = JwtAuthHelper.getPayload(accessToken);
        // set authentication info into SecurityContextHolder
        UserDetails userDetails = CostumeUserDetailsService.loadUserById(payload.getUserId());
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    } catch(Exception e){
      logger.error("Cannot set user authentication: {}", e);
    }
    // continue
    filterChain.doFilter(request, response);
  }


  private String parseJwt(HttpServletRequest request) {
    String headerAuth = request.getHeader("Authorization");

    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
      return headerAuth.substring(7, headerAuth.length());
    }
    return null;
  }

}
