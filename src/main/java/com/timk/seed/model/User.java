package com.timk.seed.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "user_profile", uniqueConstraints = @UniqueConstraint(columnNames = {"login"}))
public class User {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id", insertable = false, updatable = false)
  private Long id;

  @Column(name = "login")
  private String login;

  @Column(name = "password")
  private String password;

  @Column(name = "name")
  private String name;

  @ManyToMany
  @JoinTable(name = "user_access_group",
      joinColumns = {@JoinColumn(name = "user_id")},
      inverseJoinColumns = {@JoinColumn(name = "access_group_id")})
  private Set<AccessGroup> accessGroups = new HashSet<>();
}
