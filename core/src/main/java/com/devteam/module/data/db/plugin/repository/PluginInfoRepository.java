
package com.devteam.module.data.db.plugin.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devteam.module.data.db.plugin.entity.PluginInfo;

public interface PluginInfoRepository extends JpaRepository<PluginInfo, Serializable> {
  @Query("SELECT p FROM PluginInfo p WHERE module = :module AND service = :service AND type = :type")
  PluginInfo getOne(@Param("module") String module, @Param("service") String service, @Param("type") String type);

  @Query("SELECT p FROM PluginInfo p WHERE module = :module AND service = :service")
  List<PluginInfo> findPluginInfos(@Param("module") String module, @Param("service") String service);
}