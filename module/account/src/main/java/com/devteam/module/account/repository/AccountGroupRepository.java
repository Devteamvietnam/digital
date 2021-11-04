
package com.devteam.module.account.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devteam.module.account.entity.AccountGroup;

@Repository
public interface AccountGroupRepository extends JpaRepository<AccountGroup, Serializable> {

  @Query("SELECT g FROM AccountGroup g WHERE g.path = :path")
  public AccountGroup getByPath(@Param("path") String path);

  @Query("SELECT g FROM AccountGroup g WHERE g.parentPath is null")
  public List<AccountGroup> findRootChildren();


  @Query("SELECT g FROM AccountGroup g WHERE g.parentPath = :path")
  public List<AccountGroup> findChildren(@Param("path") String path);
}