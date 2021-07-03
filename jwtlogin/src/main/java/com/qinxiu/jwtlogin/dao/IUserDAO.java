package com.qinxiu.jwtlogin.dao;

import com.qinxiu.jwtlogin.model.User;
import java.util.Set;
import javax.swing.text.html.parser.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDAO extends JpaRepository<User, Integer> {


  User findById(Integer id);

  User findByEmail(String email);

  Set<User> findByRole(String role);
}
