package com.qinxiu.jwtlogin.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/content")
public class ContentController {


  @GetMapping("/super")
  @PreAuthorize("hasRole('SUPER')")
  public String superV(){
    return "super access";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER')")
  public String admin(){
    return "admin access";
  }

  @GetMapping("/all")
  public String normal()
  {
    return "basic access";
  }


}
