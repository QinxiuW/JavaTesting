package com.qinxiu.jwtlogin.dao.test;

import static org.junit.Assert.*;

import com.qinxiu.jwtlogin.JwtloginTestApplication;
import com.qinxiu.jwtlogin.dao.IUserDAO;
import com.qinxiu.jwtlogin.model.User;
import java.util.Set;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

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
    userDAO.save(user);

    // Assert
    assertTrue(userDAO.exists(1));
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
}
