package com.qinxiu.jwtlogin.controller;


import com.qinxiu.jwtlogin.dao.IUserDAO;
import com.qinxiu.jwtlogin.dto.LoginParamDto;
import com.qinxiu.jwtlogin.helper.JwtAuthHelper;
import com.qinxiu.jwtlogin.helper.customeException.BusinessException;
import com.qinxiu.jwtlogin.helper.customeException.BusinessStatus;
import com.qinxiu.jwtlogin.helper.customeException.ResponseResult;
import com.qinxiu.jwtlogin.model.User;
import com.qinxiu.jwtlogin.security.entity.JwtPayload;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Value("${jwtAuth.config.secret}")
  private String secret;

  @Value("${jwtAuth.config.expirationMs}")
  private int expirationMs;

  @Resource
  private IUserDAO userDAO;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @PostMapping("/login")
  public ResponseResult<Map<String, Object>> login(@RequestBody LoginParamDto loginParamDto) {
    //validate loginParam
    validateLoginParam(loginParamDto);

    // find the user
    User user = userDAO.findByEmail(loginParamDto.getEmail());
    if (user == null) {
      throw new BusinessException(BusinessStatus.USER_NOT_FOUND);
    }

    // authentication
    boolean isMatched = passwordEncoder.matches(loginParamDto.getPwd(), user.getPassword());
    if (!isMatched) {
      throw new BusinessException(BusinessStatus.AUTH_AUTHENTICATION_ERROR);
    }

    // generate JWT token
    JwtPayload payload = new JwtPayload(user.getId(), user.getRole());
    String token = JwtAuthHelper.generateToken(payload, secret, expirationMs);

    // return the result
    Map<String, Object> result = new HashMap<String, Object>();
    result.put("token", token);
    result.put("userInfo", user);

    return ResponseResult.<Map<String, Object>>builder().code(BusinessStatus.OK.getCode())
        .message(BusinessStatus.OK.getMessage()).data(result).build();
  }


  private void validateLoginParam(LoginParamDto loginParam) {
    if (loginParam == null
        || StringUtils.isEmpty(loginParam.getEmail())
        || StringUtils.isEmpty(loginParam.getPwd())) {
      throw new BusinessException(BusinessStatus.AUTH_INVALID_LOGIN_PARAM);
    }
  }


  @GetMapping("/ping")
  public String ping() {
    return "authController pong";
  }
}
