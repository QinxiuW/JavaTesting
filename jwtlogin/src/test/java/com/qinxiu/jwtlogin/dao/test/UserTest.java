package com.qinxiu.jwtlogin.dao.test;

import static org.junit.Assert.*;

import com.qinxiu.jwtlogin.JwtloginTestApplication;
import com.qinxiu.jwtlogin.dao.IUserDAO;
import com.qinxiu.jwtlogin.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JwtloginTestApplication.class)
@Transactional
@Rollback
public class UserTest {

  @Resource
  IUserDAO userDAO;

  @Test
  public void insertTest() {
    // Arrange
    User user = User.builder().id(1).email("email").password("123").name("name").role("Admin").build();

    // Act
    User result = userDAO.save(user);

    // Assert
    Assertions.assertEquals(user,result);
  }

  @Test
  public void getOneTest() {
    // Arrange
    User user = User.builder().id(1).email("email").password("123").name("name")
        .role("Admin").build();

    // Act
    userDAO.save(user);

    // Assert
    assertEquals(user, userDAO.getOne(1));
  }


  @Test
  public void findByIdTest() {
    // Arrange
    User user = User.builder().id(1).email("email").password("123").name("name")
        .role("Admin").build();

    // Act
    userDAO.save(user);

    // Assert
    assertEquals(user, userDAO.findById(1));
  }

  @Test
  public void findByEmail() {
    // Arrange
    User user = User.builder().id(1).email("email").password("123").name("name")
        .role("Admin").build();

    // Act
    userDAO.save(user);

    // Assert
    assertEquals(user, userDAO.findByEmail("email"));
  }


  @Test
  public void findByRole() {
    //Arrange
    User user1 = User.builder().id(1).email("email").password("123").name("user_01").role("Admin").build();
    User user2 = User.builder().id(2).email("email").password("123").name("user_02").role("Admin").build();

    // Act
    userDAO.save(user1);
    userDAO.save(user2);

    // Assert
    Set<User> result = userDAO.findByRole("Admin");
    assertNotNull(result);
    assertEquals(2, result.size());
  }

  @Test
  public void asyncTest() throws ExecutionException, InterruptedException {
    User user = User.builder().id(1).email("email").password("123").name("name").role("Admin").build();
    userDAO.save(user);
    long start = System.currentTimeMillis();

    Future<User> future = userDAO.findByNameAsync("name");
    User response =future.get();

    System.out.println(("Elapsed time: " + (System.currentTimeMillis() - start)));
    System.out.println(response);
  }


  @Test
  public void asyncListTest() throws ExecutionException, InterruptedException {
    User user = User.builder().id(1).email("email").password("123").name("name").role("Admin").build();
    userDAO.save(user);
    long start = System.currentTimeMillis();

    List<Future<User>> futures = new ArrayList<>();
    for (int i = 1; i <= 10; i++) {
      Future<User> future =  userDAO.findByNameAsync("name");
      futures.add(future);
    }

    List<User> response = new ArrayList<>();
    for (Future<User> future : futures)
      response.add(future.get());

    System.out.println(("Elapsed time: " + (System.currentTimeMillis() - start)));
    System.out.println(response);
  }
}
