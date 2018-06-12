package com.timk.seed.model;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "access_role")
public class AccessRole {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id", insertable = false, updatable = false)
  private Long id;

  @Column(name = "name")
  private String name;
}
