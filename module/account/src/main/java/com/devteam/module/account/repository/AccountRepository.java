package com.devteam.module.account.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devteam.module.account.entity.Account;
import com.devteam.module.enums.StorageState;

public interface AccountRepository extends JpaRepository<Account, Serializable>  {
  public Account getByLoginId(String loginId) ;
  public Account getByEmail(String email) ;

  @Query("SELECT e from Account e WHERE  e.loginId = :loginId")
  public Account getAccountByLoginId(@Param("loginId") String loginId);

  @Modifying
  @Query("update Account a SET a.storageState = :state WHERE a.loginId = :loginId")
  int setStorageState(@Param("loginId") String loginId, @Param("state") StorageState state);

  @Query("SELECT a FROM Account a WHERE a.id IN :ids")
  public List<Account> findAccounts(@Param("ids") List<Long> ids) ;
}