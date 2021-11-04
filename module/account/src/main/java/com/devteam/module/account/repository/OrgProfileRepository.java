package com.devteam.module.account.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devteam.module.account.entity.OrgProfile;
import com.devteam.module.enums.StorageState;

public interface OrgProfileRepository extends JpaRepository<OrgProfile, Serializable> {
  public OrgProfile getByLoginId(String loginId);

  @Modifying
  @Query("update OrgProfile a SET a.storageState = :state WHERE a.loginId = :loginId")
  int setStorageState(@Param("loginId") String loginId, @Param("state") StorageState state);

  public long deleteByLoginId(String loginId);
}
