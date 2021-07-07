package com.qinxiu.jwtlogin.controller.test;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.google.gson.Gson;
//import com.qinxiu.jwtlogin.JwtloginTestApplication;
import com.qinxiu.jwtlogin.JwtloginTestApplication;
import com.qinxiu.jwtlogin.controller.UserController;
import com.qinxiu.jwtlogin.dto.UserRegDto;
import com.qinxiu.jwtlogin.helper.customeException.BusinessStatus;
import com.qinxiu.jwtlogin.helper.customeException.ResponseResult;
import com.qinxiu.jwtlogin.model.User;
import com.qinxiu.jwtlogin.services.IUserService;
import java.nio.charset.StandardCharsets;
import lombok.var;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;

//@RunWith(SpringRunner.class)
@SpringBootTest(classes = JwtloginTestApplication.class)
@WebAppConfiguration
public class UserControllerTest {


  @InjectMocks
  private UserController userController;

  @Mock
  private IUserService userService;

  @Mock
  private BCryptPasswordEncoder passwordEncoder;

//  @Resource
  private Gson gson = new Gson();

  @Test
  void test() throws Exception {

    User user = User.builder()
        .id(1)
        .email("email")
        .password("123")
        .name("name")
        .role("Admin")
        .build();

    ResponseResult<User> responseResult = ResponseResult.<User>builder()
        .code(BusinessStatus.OK.getCode())
        .message(BusinessStatus.OK.getMessage())
        .data(user)
        .build();

    String jsonString = gson.toJson(responseResult);

    Gson gson = new Gson();
    var result = gson.fromJson(jsonString, responseResult.getClass());
    System.out.println(result);
  }


  @Test
  void assertTest() throws Exception {
    CustomMvcTestHelper mvcAssert = new CustomMvcTestHelper(userController);

    MvcResult result = mvcAssert.doGetMvcRequest("/ping");

    System.out.print(result.getResponse().getContentAsString(StandardCharsets.UTF_8));
  }


  @Test
  void regTest() throws Exception {
    // Arrange
    CustomMvcTestHelper mvcAssert = new CustomMvcTestHelper(userController);
    User user = User.builder().id(1).email("email").password("123").name("name")
        .role("Admin").build();
    User expectedData =  User.builder().id(1).email("email").name("name").role("Admin").build();
    UserRegDto userReg = UserRegDto.builder().email("email").password("123").name("name").build();
    Mockito.when(userService.registerUser(any(User.class))).thenReturn(user);
    when(passwordEncoder.encode(anyString())).thenReturn("*********");

    // Act
    MvcResult result = mvcAssert.doPostMvcRequest("/api/reg", userReg);

    // Assert

    mvcAssert.<User>assertMvcResult(result,BusinessStatus.OK,expectedData);
  }

  @Test
  void pingTest() throws Exception {
    //Arrange
    CustomMvcTestHelper mvcAssert = new CustomMvcTestHelper(userController);
    String expectedData = "user controller pong";

    // Act
    MvcResult result = mvcAssert.doGetMvcRequest("/ping");

    // Assert
    mvcAssert.assertMvcResultJson(result,expectedData);


  }
}

