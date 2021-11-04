package com.devteam.module.account.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devteam.module.account.entity.UserWork;

public interface UserWorkRepository extends JpaRepository<UserWork, Serializable> {

  @Modifying
  @Query("DELETE FROM UserWork i WHERE i.loginId = :loginId AND i.id NOT IN (:validIdSet)")
  int deleteOrphan(@Param("loginId") String loginId, @Param("validIdSet") Set<Long> validIdSet);

  public List<UserWork> findByLoginId(String loginId);

  @Modifying
  @Query("DELETE FROM UserWork i WHERE i.loginId = :loginId AND i.id IN (:ids)")
  int delete(@Param("loginId") String loginId, @Param("ids") List<Long> ids);
}
