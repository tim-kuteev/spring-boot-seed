package com.timk.seed.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class AuthUserDto {
  private Long id;
  private String login;
  private String password;
  private Set<AccessGroupDto> accessGroups = new HashSet<>();

  public AuthUserDto(AuthUserDto source) {
    if (source == null) {
      return;
    }
    setId(source.getId());
    setLogin(source.getLogin());
    setPassword(source.getPassword());
    setAccessGroups(source.getAccessGroups());
  }
}
