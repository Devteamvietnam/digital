package com.devteam.module.account.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devteam.module.account.entity.UserRelation;

public interface UserRelationRepository extends JpaRepository<UserRelation, Serializable> {

  @Modifying
  @Query("DELETE FROM UserRelation r WHERE r.loginId = :loginId AND r.id NOT IN (:validIdSet)")
  int deleteOrphan(@Param("loginId") String loginId, @Param("validIdSet") Set<Long> validIdSet);

  public List<UserRelation> findByLoginId(String loginId);

  public long deleteByLoginId(String loginId);

  @Modifying
  @Query("DELETE FROM UserRelation r WHERE r.loginId = :loginId AND r.id IN (:ids)")
  int delete(@Param("loginId") String loginId, @Param("ids") List<Long> ids);
}
