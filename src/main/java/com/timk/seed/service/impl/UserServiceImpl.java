package com.timk.seed.service.impl;

import com.timk.seed.dto.AuthUserDto;
import com.timk.seed.model.User;
import com.timk.seed.model.repository.UserRepository;
import com.timk.seed.rest.UserController;
import com.timk.seed.security.CustomUserDetails;
import com.timk.seed.service.UserService;
import com.timk.seed.service.generic.CRUDServiceImpl;
import com.timk.seed.service.mapper.OrikaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

@Service
public class UserServiceImpl extends CRUDServiceImpl<User, Long> implements UserService, UserDetailsService {

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserRepository repository;

  @Override
  protected UserRepository getRepository() {
    return repository;
  }

  @Override
  @Transactional(readOnly = true, propagation = SUPPORTS)
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    User user = repository.findByLogin(login);
    if (user == null)
      throw new UsernameNotFoundException(String.format("User '%s' not found.", login));
    logger.info("User found: {}", user.getId());
    AuthUserDto authUserDto = new OrikaMapper().map(user, AuthUserDto.class);
    return new CustomUserDetails(authUserDto);
  }
}
