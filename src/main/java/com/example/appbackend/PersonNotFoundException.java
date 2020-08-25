package com.example.appbackend;

class PersonNotFoundException extends RuntimeException {

  /**
   *
   */
  private static final long serialVersionUID = -8515071101128219932L;

  PersonNotFoundException(String email) {
    super("Failed to login: " + email);
  }
}