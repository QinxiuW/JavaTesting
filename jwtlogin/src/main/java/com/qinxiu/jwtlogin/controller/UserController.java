package com.qinxiu.jwtlogin.controller;


import com.qinxiu.jwtlogin.dao.IUserDAO;
import com.qinxiu.jwtlogin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  IUserDAO userDAO;

  @GetMapping("test")
  public String test() {

    User user = User.builder().email("email").password("123").name("name").role("Admin").build();
    userDAO.save(user);
    return "ok";
  }


  @GetMapping("ping")
  public String ping() {

    return "pong";
  }
}
