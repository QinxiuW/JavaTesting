package com.qinxiu.jwtlogin.helper.customeException;

public enum BusinessStatus {
  UNKNOWN(-1, "UNKNOWN"),
  OK(2000, "Request successful"),

  //=========================================================================================
  //                800 - 809 -> User status code
  //=========================================================================================
  USER_INSERTION_ERROR(800, "User insertion error"),
  USER_NOT_FOUND(801, "User not found"),
  USER_INVALID_USER_REG(802,"Invalid User Registration data"),


  //=========================================================================================
  //                810 - 819 -> Auth status code
  //=========================================================================================
  AUTH_INVALID_LOGIN_PARAM(810, "Invalid login param"),
  AUTH_AUTHENTICATION_ERROR(811, "Authentication error");


  private final Integer code;
  private final String message;

  BusinessStatus(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

  public Integer getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  /**
   * Get exception info by code.
   *
   * @param code {@code int}
   * @return {@code String}
   */
  public static String getMessage(int code) {
    for (BusinessStatus status : values()) {
      if (status.getCode().equals(code)) {
        return status.getMessage();
      }
    }
    return null;
  }
}
