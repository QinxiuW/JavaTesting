package com.qinxiu.jwtlogin.security.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface ICostumeUserDetailsService {
  UserDetails loadUserByUsername(String username);
  UserDetails loadUserByEmail(String email);
  UserDetails loadUserById(Integer id);
}
