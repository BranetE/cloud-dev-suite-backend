package com.branet.cloud.dev.suite.authservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** The type Register request. */
@Getter
@Setter
@NoArgsConstructor
public class RegisterRequest {
  /** The Email. */
  String email;

  /** The First name. */
  String firstName;

  /** The Last name. */
  String lastName;

  /** The Position. */
  String position;

  /** The Password. */
  String password;

  /** The Experience. */
  String experience;
}
