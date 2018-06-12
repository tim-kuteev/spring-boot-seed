package com.timk.seed.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "access_group")
public class AccessGroup {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id", insertable = false, updatable = false)
  private Long id;

  @Column(name = "name")
  private String name;

  @ManyToMany
  @JoinTable(name = "access_group_access_role",
      joinColumns = {@JoinColumn(name = "access_group_id")},
      inverseJoinColumns = {@JoinColumn(name = "access_role_id")})
  private Set<AccessRole> accessRoles = new HashSet<>();
}
