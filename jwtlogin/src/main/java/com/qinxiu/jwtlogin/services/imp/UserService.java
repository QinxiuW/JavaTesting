package com.qinxiu.jwtlogin.services.imp;

import com.qinxiu.jwtlogin.dao.IUserDAO;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Resource
  private IUserDAO userDAO;


}
