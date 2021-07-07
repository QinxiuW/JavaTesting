package com.qinxiu.jwtlogin.helper.customeException;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@Builder
public class ResponseResult<T> implements Serializable {


  private static final long serialVersionUID = -6029291628888865543L;

  private Integer code;

  private String message;

  private T data;
}
