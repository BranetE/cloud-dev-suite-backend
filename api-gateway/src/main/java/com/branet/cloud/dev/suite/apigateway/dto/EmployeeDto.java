package com.branet.cloud.dev.suite.apigateway.dto;

/**
 * The type Employee dto.
 */
public class EmployeeDto {
  private Long id;
  private String email;
  private String password;
  private String role;

  /**
   * Gets id.
   *
   * @return the id
   */
public Long getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
public void setId(Long id) {
    this.id = id;
  }

  /**
   * Gets email.
   *
   * @return the email
   */
public String getEmail() {
    return email;
  }

  /**
   * Sets email.
   *
   * @param email the email
   */
public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Gets password.
   *
   * @return the password
   */
public String getPassword() {
    return password;
  }

  /**
   * Sets password.
   *
   * @param password the password
   */
public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Gets role.
   *
   * @return the role
   */
public String getRole() {
    return role;
  }

  /**
   * Sets role.
   *
   * @param role the role
   */
  public void setRole(String role) {
    this.role = role;
  }
}
