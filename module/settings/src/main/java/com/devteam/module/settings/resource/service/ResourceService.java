package com.devteam.module.settings.resource.service;

import java.util.List;

import com.devteam.module.settings.resource.logic.ResourceEntityLogic;
import com.devteam.module.settings.resource.logic.ResourceTypeLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.entity.ChangeStorageStateRequest;
import com.devteam.module.data.db.query.SqlQueryParams;
import com.devteam.module.settings.resource.entity.ResourceEntity;
import com.devteam.module.settings.resource.entity.ResourceTag;
import com.devteam.module.settings.resource.entity.ResourceType;

@Service
public class ResourceService {

  @Autowired
  private ResourceEntityLogic resourceEntityLogic;

  @Autowired
  private ResourceTypeLogic resourceTypeLogic;

  @Transactional
  public ResourceEntity getResourceEntityByCodeAndResourceType(ClientInfo client, String code, String resourceType) {
    return resourceEntityLogic.getByCodeAndResourceType(client, code, resourceType);
  }

  @Transactional
  public ResourceEntity saveResourceEntity(ClientInfo client, ResourceEntity resourceEntity) {
    return resourceEntityLogic.save(client, resourceEntity);
  }

  @Transactional
  public Boolean deleteResourceEntity(ClientInfo client, Long ... id) {
    return resourceEntityLogic.delete(client, id);
  }

  @Transactional
  public List<ResourceEntity> searchResourceEntityByType(ClientInfo client, SqlQueryParams params) {
    return resourceEntityLogic.searchResourceEntityByType(client, params);
  }

  @Transactional
  public boolean changeResourceEntityStorageState(ClientInfo client, ChangeStorageStateRequest req) {
    return resourceEntityLogic.changeStorageState(client, req);
  }

  @Transactional
  public ResourceTag saveResourceTag(ClientInfo client, ResourceTag resourceTag) {
    return resourceTypeLogic.saveResourceTag(client, resourceTag);
  }

  @Transactional
  public ResourceTag getResourceTag(ClientInfo client, String resourceTagName) {
    return resourceTypeLogic.getResourceTag(client, resourceTagName);
  }

  @Transactional
  public ResourceType getResourceTypeByType(ClientInfo client, String type) {
    return resourceTypeLogic.getByType(client, type);
  }

  @Transactional
  public ResourceType saveResourceType(ClientInfo client, ResourceType resourceType) {
    return resourceTypeLogic.save(client, resourceType);
  }

  @Transactional
  public List<ResourceType> searchResourceType(ClientInfo client, SqlQueryParams params) {
    return resourceTypeLogic.searchType(client, params);
  }

  @Transactional
  public boolean changeResourceTypeStorageState(ClientInfo client, ChangeStorageStateRequest req) {
    return resourceTypeLogic.changeStorageState(client, req);
  }
}
