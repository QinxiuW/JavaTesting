package com.qinxiu.jwtlogin.dao.test;




import com.qinxiu.jwtlogin.JwtloginTestApplication;
import com.qinxiu.jwtlogin.dao.IUserDAO;
import com.qinxiu.jwtlogin.model.User;
import java.util.Set;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;


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
    Assertions.assertNotNull(result);
//    assertTrue(userDAO.exists(1));
  }

  @Test
  public void getOneTest() {
    // Arrange
    User user = User.builder().id(1).email("email").password("123").name("name")
        .role("Admin").build();

    // Act
    userDAO.save(user);

    // Assert
    Assertions.assertEquals(user, userDAO.getOne(1));
  }


  @Test
  public void findByIdTest() {
    // Arrange
    User user = User.builder().id(1).email("email").password("123").name("name")
        .role("Admin").build();

    // Act
    userDAO.save(user);

    // Assert
    Assertions.assertEquals(user, userDAO.findById(1));
  }

  @Test
  public void findByEmail() {
    // Arrange
    User user = User.builder().id(1).email("email").password("123").name("name")
        .role("Admin").build();

    // Act
    userDAO.save(user);

    // Assert
    Assertions.assertEquals(user, userDAO.findByEmail("email"));
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
    Assertions.assertNotNull(result);
    Assertions.assertEquals(2, result.size());
  }
}
