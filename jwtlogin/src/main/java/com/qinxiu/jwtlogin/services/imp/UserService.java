package com.qinxiu.jwtlogin.services.imp;

import com.qinxiu.jwtlogin.dao.IUserDAO;
import com.qinxiu.jwtlogin.model.User;
import com.qinxiu.jwtlogin.services.IUserService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

  @Resource
  private IUserDAO userDAO;


  @Override
  public User registerUser(User user) {
    return userDAO.save(user);
  }

  @Override
  public int updateUser(User user) {
    return 0;
  }

  @Override
  public User getByEmail(String email) {
    return userDAO.findByEmail(email);
  }

  @Override
  public User getById(int id) {
    return userDAO.findById(id);
  }

  @Override
  public List<User> getAll() {
    return userDAO.findAll();
  }

  @Override
  public int deleteUser(int id) {
    User user = userDAO.findById(id);
    if (user !=null){
      userDAO.delete(user);
    }
    return 1;
  }
}
