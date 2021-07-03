package com.qinxiu.jwtlogin.services;

import com.qinxiu.jwtlogin.model.User;
import java.util.Set;

public interface IUserService {

  /**
   * Register the user.
   * @param user {@link User} User object
   * @return {@code Integer} 1: success , 0: failed
   */
  int registerUser(User user);

  /**
   * Update the corresponding user properties.
   * @param user {@link User} User object
   * @return {@code Integer} 1: success , 0: failed
   */
  int updateUser(User user);

  /**
   * Find the User by Email property.
   * @param email {@code String}
   * @return {@link User} User Object.
   */
  User getByEmail(String email);

  /**
   * Find the User by id.
   * @param id {@code Integer} User's identifier
   * @return {@link User} User Object
   */
  User getById(Integer id);

  /**
   * Find all users.
   * @return {@link Set<User>}
   */
  Set<User> getAll();

  /**
   * Delete a user by it id.
   * @param id {@code Integer} User's identifier
   * @return {@code Integer} 1: success , 0: failed
   */
  int deleteUser(Integer id);
}
