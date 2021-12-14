package com.qinxiu.jwtlogin.dao;

import com.qinxiu.jwtlogin.model.User;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.stream.IntStream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

public interface IUserDAO extends JpaRepository<User, Integer> {

  User findById(int id);

  User findByName(String name);

  @Async
  default Future<User> findByNameAsync(String name) throws InterruptedException {
    IntStream.range(0,1000).forEach(x-> findByName(name));
    return new AsyncResult<>(findByName(name));
  }

  User findByEmail(String email);

  Set<User> findByRole(String role);
}
