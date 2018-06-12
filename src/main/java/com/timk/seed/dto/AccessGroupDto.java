package com.timk.seed.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class AccessGroupDto {
  private Long id;
  private String name;
  private Set<AccessRoleDto> accessRoles = new HashSet<>();
}
