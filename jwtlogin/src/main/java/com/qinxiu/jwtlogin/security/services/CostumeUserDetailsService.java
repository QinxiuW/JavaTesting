package com.qinxiu.jwtlogin.security.services;

import com.qinxiu.jwtlogin.dao.IUserDAO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import lombok.NoArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class CostumeUserDetailsService implements ICostumeUserDetailsService {

  @Autowired
  private IUserDAO userDAO;

  /**
   * Load the user details by name.
   *
   * @param username {@code String} name.
   * @return {@link UserDetails}
   * @throws UsernameNotFoundException exception
   */
  public UserDetails loadUserByUsername(String username) {

    // find the user by email
    var user = userDAO.findByName(username);
    return loadUser(user);
  }

  /**
   * Load the user details by email.
   *
   * @param email {@code String}
   * @return {@link UserDetails}
   * @throws UsernameNotFoundException exception
   */
  public UserDetails loadUserByEmail(String email) {

    // find the user by email
    var user = userDAO.findByEmail(email);
    return loadUser(user);
  }


  /**
   * Load the user details by id.
   *
   * @param id {@code Integer}
   * @return {@link UserDetails}
   * @throws UsernameNotFoundException exception
   */
  public UserDetails loadUserById(Integer id) throws UsernameNotFoundException {

    // find the user by email
    var user = userDAO.findById(id);
    return loadUser(user);
  }


  private User loadUser(com.qinxiu.jwtlogin.model.User user) {
    if (user == null) {
      //throw error
      return null;
    }
    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    if (user.getRole() != null) {
      grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
    }
    return new User(user.getName(), user.getPassword(), grantedAuthorities);
  }

}
