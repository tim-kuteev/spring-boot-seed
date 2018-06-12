package com.timk.seed.security;

import com.timk.seed.dto.AccessRoleDto;
import com.timk.seed.dto.AuthUserDto;
import com.timk.seed.dto.AccessGroupDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import static java.util.stream.Collectors.toSet;

public class CustomUserDetails extends AuthUserDto implements UserDetails {

  private Collection<GrantedAuthority> authorities;

  public CustomUserDetails(AuthUserDto authUserDto) {
    super(authUserDto);
    authorities = authUserDto.getAccessGroups().stream()
        .map(AccessGroupDto::getAccessRoles)
        .flatMap(Collection::stream)
        .map(AccessRoleDto::getName)
        .map(SimpleGrantedAuthority::new)
        .collect(toSet());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return super.getPassword();
  }

  @Override
  public String getUsername() {
    return super.getLogin();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
