package com.devteam.module.account.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.devteam.module.data.db.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@JsonInclude(Include.NON_NULL)
@Getter @Setter
public class BaseProfile extends BaseEntity<Long> {
  @Column(name="login_id", unique =true)
  protected String loginId;
  protected String email;
  protected String mobile;
  @Column(name = "avatar_url")
  protected String avatarUrl;

}
