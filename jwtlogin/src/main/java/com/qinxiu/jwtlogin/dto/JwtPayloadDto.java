package com.qinxiu.jwtlogin.dto;

import javax.persistence.Entity;
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
public class JwtPayloadDto {

  /**
   * User's identifier.
   */
  private Integer userId;

  /**
   * authority role.
   */
  private String role;
}
