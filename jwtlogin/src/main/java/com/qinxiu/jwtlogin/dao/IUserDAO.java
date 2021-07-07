package com.qinxiu.jwtlogin.dao;

import com.qinxiu.jwtlogin.model.User;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDAO extends JpaRepository<User, Integer> {

  User findById(int Id);

  User findByName(String name);

  User findByEmail(String email);

  Set<User> findByRole(String role);
}
