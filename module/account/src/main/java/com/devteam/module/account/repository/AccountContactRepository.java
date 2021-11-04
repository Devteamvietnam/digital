
package com.devteam.module.account.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devteam.module.account.entity.AccountContact;
import com.devteam.module.enums.StorageState;

public interface AccountContactRepository extends JpaRepository<AccountContact, Serializable> {
  public List<AccountContact> findByLoginId(String loginId);

  @Modifying
  @Query("update AccountContact c SET c.storageState = :state WHERE c.loginId = :loginId")
  int setStorageState(@Param("loginId") String loginId, @Param("state") StorageState state);

  @Modifying
  @Query("DELETE FROM AccountContact c WHERE c.loginId = :loginId AND c.id NOT IN (:validIdSet)")
  int deleteOrphan(@Param("loginId") String loginId, @Param("validIdSet") Set<Long> validIdSet);

  @Modifying
  @Query("DELETE FROM AccountContact c WHERE c.loginId = :loginId AND c.id IN (:ids)")
  int delete(@Param("loginId") String loginId, @Param("ids") List<Long> ids);

  public long deleteByLoginId(String loginId);
}