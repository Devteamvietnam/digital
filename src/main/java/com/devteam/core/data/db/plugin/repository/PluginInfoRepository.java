package com.devteam.core.data.db.plugin.repository;

import com.devteam.core.data.db.plugin.entity.PluginInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.List;

public interface PluginInfoRepository extends JpaRepository<PluginInfo, Serializable> {
    @Query("SELECT p FROM PluginInfo p WHERE module = :module AND service = :service AND type = :type")
    PluginInfo getOne(@Param("module") String module, @Param("service") String service, @Param("type") String type);

    @Query("SELECT p FROM PluginInfo p WHERE module = :module AND service = :service")
    List<PluginInfo> findPluginInfos(@Param("module") String module, @Param("service") String service);
}