package com.devteam.module.account.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devteam.module.account.entity.AccountMembership;
import com.devteam.module.enums.StorageState;

@Repository
public interface AccountMembershipRepository extends JpaRepository<AccountMembership, Serializable> {
  public List<AccountMembership> findByGroupPath(String groupPath);
  public List<AccountMembership> findByLoginId(String loginId);

  public AccountMembership getMembershipByGroupPathAndLoginId(@Param("groupPath") String groupPath, @Param("loginId") String loginId);

  @Modifying
  @Query("update AccountMembership m SET m.storageState = :state WHERE m.loginId = :loginId")
  int setStorageState(@Param("loginId") String loginId, @Param("state") StorageState state);

  @Modifying
  @Query("Delete from AccountMembership m where m.groupPath LIKE :groupPath")
  public void deleteByGroup(@Param("groupPath") String groupPath);

  public long deleteByLoginId(String loginId);
}