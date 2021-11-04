package com.devteam.module.settings.location.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devteam.module.enums.StorageState;
import com.devteam.module.settings.location.entity.LocationType;

@Repository
public interface LocationTypeRepository extends JpaRepository<LocationType, Serializable> {

  public LocationType getByType(String type);

  @Modifying
  @Query("UPDATE LocationType lt SET lt.storageState = :state WHERE lt.type = :type ")
  int setLocationTypeStorageState(@Param("state") StorageState state, @Param("type") String type);

  @Query("SELECT lt FROM LocationType lt WHERE lt.id IN :ids")
  public List<LocationType> findLocationTypes(@Param("ids") List<Long> ids);
}
