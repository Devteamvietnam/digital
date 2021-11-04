package com.devteam.module.data.db.plugin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.plugin.entity.PluginInfo;
import com.devteam.module.data.db.plugin.repository.PluginInfoRepository;

@Service
public class ServicePluginService {
  @Autowired
  private PluginInfoRepository repo;

  public PluginInfo getPlugin(ClientInfo client, String module, String service, String type) {
    return repo.getOne(module, service, type);
  }

  public List<PluginInfo> find(ClientInfo client, String module, String service) {
    return repo.findPluginInfos(module, service);
  }

  public List<PluginInfo> findAll(ClientInfo client) {
    return repo.findAll();
  }
}
