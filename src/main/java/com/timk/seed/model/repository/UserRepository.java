package com.timk.seed.model.repository;

import com.timk.seed.model.User;
import com.timk.seed.model.repository.generic.RootRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends RootRepository<User, Long> {

  User findByLogin(String login);
}
