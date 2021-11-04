package com.devteam.module.settings.resource.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devteam.module.enums.StorageState;
import com.devteam.module.settings.resource.entity.ResourceEntity;

public interface ResourceEntityRepository extends JpaRepository<ResourceEntity, Serializable> {

  ResourceEntity getByCodeAndResourceType(String code, String resourceType);

  @Modifying
  @Query("UPDATE ResourceEntity re SET re.storageState = :storageState WHERE re.id IN :ids")
  int setResourceEntityState(@Param("storageState") StorageState state, @Param("ids")  List<Long> ids);
}
