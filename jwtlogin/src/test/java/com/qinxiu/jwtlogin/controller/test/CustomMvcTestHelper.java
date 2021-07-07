package com.qinxiu.jwtlogin.controller.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.qinxiu.jwtlogin.helper.customeException.BusinessStatus;
import com.qinxiu.jwtlogin.helper.customeException.ResponseResult;
import com.qinxiu.jwtlogin.model.User;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import javax.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@Data
public class CustomMvcTestHelper {

  @Resource
  private MockMvc mockMvc;

  private static Gson gson = new Gson();

  public CustomMvcTestHelper(Object controllerObj) {
    this.mockMvc = MockMvcBuilders.standaloneSetup(controllerObj).build();
  }


  public MvcResult doGetMvcRequest(String url) throws Exception {
    return mockMvc.perform(MockMvcRequestBuilders
        .get(url)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status()
            .isOk())
        .andReturn();

  }

  public MvcResult doPostMvcRequest(String url, Object requestBodyObj) throws Exception {
    String jsonString = gson.toJson(requestBodyObj);

    return mockMvc.perform(MockMvcRequestBuilders.post(url)
        .content(jsonString)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status()
            .isOk())
        .andReturn();
  }

  public <T> void assertMvcResult(MvcResult result,BusinessStatus status, Object obj)
      throws UnsupportedEncodingException {

    // get the result json string
    String resultJson = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
    Assertions.assertNotNull(resultJson);

    // cast the json string to obj
    ResponseResult<T> userResponseResult =gson.fromJson(resultJson, ResponseResult.class);
    Assertions.assertEquals(BusinessStatus.OK.getCode(),userResponseResult.getCode());
    Assertions.assertEquals(BusinessStatus.OK.getMessage(),userResponseResult.getMessage());

    // second casting for T object
    String dataJson = gson.toJson(userResponseResult.getData());
    User data = gson.fromJson(dataJson, (Type) obj.getClass());
    Assertions.assertEquals(data,obj);
  }

  public void assertMvcResultJson(MvcResult result,String json)
      throws UnsupportedEncodingException {

    // get the result json string
    String resultJson = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
    Assertions.assertNotNull(resultJson);
    Assertions.assertEquals(json,resultJson);
  }


}
