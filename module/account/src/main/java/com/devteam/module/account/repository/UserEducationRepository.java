package com.devteam.module.account.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devteam.module.account.entity.UserEducation;

public interface UserEducationRepository extends JpaRepository<UserEducation, Serializable> {
  public List<UserEducation> findByLoginId(String loginId);

  @Modifying
  @Query("DELETE FROM UserEducation e WHERE e.loginId = :loginId AND e.id NOT IN (:validIdSet)")
  int deleteOrphan(@Param("loginId") String loginId, @Param("validIdSet") Set<Long> validIdSet);

  @Modifying
  @Query("DELETE FROM UserEducation e WHERE e.loginId = :loginId AND e.id IN (:ids)")
  int delete(@Param("loginId") String loginId, @Param("ids") List<Long> ids);

  public long deleteByLoginId(String loginId);
}
