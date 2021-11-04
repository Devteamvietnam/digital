
package com.devteam.module.data.db.activity.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devteam.module.data.db.activity.entity.EntityActivity;

public interface EntityActivityRepository extends JpaRepository<EntityActivity, Serializable> {
  @Query("SELECT e FROM EntityActivity e WHERE entityTable = :table AND entityId = :entityId")
  List<EntityActivity> findByEntityId(@Param("table") String table, @Param("entityId") Long entiyId);
}