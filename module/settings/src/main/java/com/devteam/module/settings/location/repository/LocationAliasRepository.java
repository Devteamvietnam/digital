package com.devteam.module.settings.location.repository;

import com.devteam.module.enums.StorageState;
import com.devteam.module.settings.location.entity.LocationAlias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface LocationAliasRepository extends JpaRepository<LocationAlias, Serializable> {

  LocationAlias getByCode(String code);

  @Modifying
  @Query("UPDATE LocationAlias lr SET lr.storageState = :storageState WHERE lr.code = :code ")
  int setLocationAliasStorageState(@Param("storageState") StorageState state, @Param("code") String code);

  @Query("SELECT lr FROM LocationAlias lr WHERE lr.id IN :ids")
  List<LocationAlias> findLocationAliases(@Param("ids") List<Long> ids);
}
