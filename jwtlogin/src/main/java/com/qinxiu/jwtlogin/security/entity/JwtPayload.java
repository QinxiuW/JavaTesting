package com.qinxiu.jwtlogin.security.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class JwtPayload {

  /**
   * User's identifier.
   */
  private Integer userId;

  /**
   * authority role.
   */
  private String role;
}
