package com.devteam.module.settings.location.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devteam.module.enums.StorageState;
import com.devteam.module.settings.location.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Serializable> {

  public Location getByCode(String code);

  @Modifying
  @Query("UPDATE Location l SET l.storageState = :state WHERE l.code = :code ")
  int setLocationState(@Param("state") StorageState state, @Param("code") String code);

  @Query("SELECT l FROM Location l WHERE l.id IN :ids")
  public List<Location> findLocations(@Param("ids") List<Long> ids);
}
