package com.devteam.module.settings.resource.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devteam.module.enums.StorageState;
import com.devteam.module.settings.resource.entity.ResourceType;

@Repository
public interface ResourceTypeRepository extends JpaRepository<ResourceType, Serializable> {
  ResourceType getByType(String type);

  @Modifying
  @Query("UPDATE ResourceType rt SET rt.storageState = :storageState WHERE rt.id IN :ids")
  int setResourceTypeState(@Param("storageState") StorageState state, @Param("ids")  List<Long> ids);
}
