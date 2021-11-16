package com.qinxiu.jwtlogin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
@EqualsAndHashCode
@Table(name = "users")
public class User {

  /**
   * user's identifier.
   */
  @Id
  private int id;

  /**
   * user's email.
   */
  private String email;

  /**
   * user's name
   */
  private String name;

  /**
   * user's encrypted password.
   */
  @JsonIgnore
  private String password;

  /**
   * user's role {ADMIN,NORMAL,SUPERVISOR}
   */
  private String role;
}
