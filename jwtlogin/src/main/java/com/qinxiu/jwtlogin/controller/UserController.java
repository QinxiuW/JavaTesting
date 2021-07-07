package com.qinxiu.jwtlogin.controller;


import com.qinxiu.jwtlogin.dao.IUserDAO;
import com.qinxiu.jwtlogin.dto.UserRegDto;
import com.qinxiu.jwtlogin.helper.customeException.BusinessException;
import com.qinxiu.jwtlogin.helper.customeException.BusinessStatus;
import com.qinxiu.jwtlogin.helper.customeException.ResponseResult;
import com.qinxiu.jwtlogin.helper.statics.UserRole;
import com.qinxiu.jwtlogin.model.User;
import com.qinxiu.jwtlogin.services.IUserService;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController

public class UserController {

  @Resource
  IUserService userService;

  @Resource
  private BCryptPasswordEncoder passwordEncoder;

  @PostMapping("/api/reg")
  public ResponseResult<User> registerUser(@RequestBody UserRegDto userReg) {

    User user = initUser(userReg);
    User result = userService.registerUser(user);

    // send email
    return ResponseResult.<User>builder().code(BusinessStatus.OK.getCode())
        .message(BusinessStatus.OK.getMessage())
        .data(result)
        .build();
  }


  private User initUser(UserRegDto userReg) {
    // validate userReg
    if (userReg == null
        || StringUtils.isEmpty(userReg.getEmail())
        || StringUtils.isEmpty(userReg.getName())
        || StringUtils.isEmpty(userReg.getPassword())){
      throw new BusinessException(BusinessStatus.USER_INVALID_USER_REG);
    }

    // mapping userReg into User
    return User.builder().name(userReg.getName())
                            .email(userReg.getEmail())
                            .password(passwordEncoder.encode(userReg.getPassword()))
                            .role(UserRole.NORMAL.name()).build();
  }

  @GetMapping("ping")
  public String ping() {
    return "user controller pong";
  }
}
