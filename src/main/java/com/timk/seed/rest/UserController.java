package com.timk.seed.rest;

import com.timk.seed.dto.AuthUserDto;
import com.timk.seed.dto.UserDto;
import com.timk.seed.model.User;
import com.timk.seed.rest.generic.BaseController;
import com.timk.seed.service.UserService;
import com.timk.seed.security.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.timk.seed.model.type.RoleType.ADMIN;
import static com.timk.seed.model.type.RoleType.USER;
import static java.lang.Integer.MAX_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = "/users", produces = APPLICATION_JSON_VALUE)
public class UserController extends BaseController {

  @Autowired
  private UserService service;

  @Secured({ADMIN, USER})
  @RequestMapping(method = GET, value = "/auth")
  public UserDto currentUser(@AuthUser AuthUserDto authUserDto) {
    return mapper.map(service.read(authUserDto.getId()), UserDto.class);
  }

  @RequestMapping(method = GET, value = "/{id:\\d+}")
  public UserDto read(@PathVariable Long id) {
    return mapper.map(service.read(id), UserDto.class);
  }

  @RequestMapping(method = GET)
  public Page<UserDto> page(@PageableDefault(value = MAX_VALUE) Pageable pageable) {
    return mapper.mapAsPage(service.page(pageable), UserDto.class, pageable);
  }

  @Secured({ADMIN})
  @RequestMapping(method = {PUT, PATCH}, value = "/{id:\\d+}")
  public UserDto update(@RequestBody UserDto value) {
    return mapper.map(service.update(mapper.map(value, User.class)), UserDto.class);
  }
}
