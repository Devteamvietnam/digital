package com.devteam.module.data.db;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.devteam.module.data.TestConfig;
import com.devteam.module.data.db.plugin.repository.PluginInfoRepository;

public class RepositoryUnitTest extends TestConfig {
  @Autowired
  PluginInfoRepository pluginRepo;

  @BeforeEach
  public void setup() throws Exception {
  }

  @Test
  public void testRepository() throws Exception {
    System.out.println("test...........");
  }

}