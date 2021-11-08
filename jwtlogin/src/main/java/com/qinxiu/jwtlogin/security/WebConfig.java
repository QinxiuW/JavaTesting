//package com.qinxiu.jwtlogin.security;
//
//import java.util.List;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@EnableWebMvc
//@Slf4j
//public class WebConfig implements WebMvcConfigurer {
//
//  @Override
//  public void addCorsMappings(CorsRegistry registry) {
//
//
//    registry.addMapping("/**")
//        .allowedMethods("POST","GET","OPTIONS")
////        .allowedOrigins("*")
//        .allowedHeaders("*")
//        .exposedHeaders("Location", "Access-Control-Allow-Origin");
//  }
//}
