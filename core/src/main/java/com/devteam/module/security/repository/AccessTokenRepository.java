package com.devteam.module.security.repository;

import java.io.Serializable;

import com.devteam.module.security.entity.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken, Serializable>{
  public AccessToken getByToken(String token);

}
