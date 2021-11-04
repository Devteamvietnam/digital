package com.devteam.module.settings.resource.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.query.SqlQueryParams;
import com.devteam.module.data.db.sample.PersistableEntityAssert;
import com.devteam.module.data.db.sample.SampleData;
import com.devteam.module.settings.resource.service.ResourceService;
import com.devteam.module.settings.resource.entity.ResourceEntity;
import com.devteam.module.settings.resource.entity.ResourceTag;
import com.devteam.module.settings.resource.entity.ResourceType;
import com.devteam.util.dataformat.DataSerializer;
import com.devteam.util.ds.AssertTool;

public class ModuleEntityAssert extends SampleData {

  @Autowired
  ResourceService service;

  public class ResourceEntityAssert extends PersistableEntityAssert<ResourceEntity> {
    public ResourceEntityAssert(ClientInfo client, ResourceEntity resourceEntity, String resourceType) {
      super(client, resourceEntity);
      this.methods = new EntityServiceMethods() {
        public ResourceEntity load() {
          return service.getResourceEntityByCodeAndResourceType(client, entity.getCode(), entity.getResourceType());
        }
        public ResourceEntity save(ResourceEntity resourceEntity) {
          service.saveResourceEntity(client, resourceEntity);
          return load();
        }
        public List<?> searchEntity() {
          SqlQueryParams params = createSearchQuery(resourceEntity.getCode());
          params.addParam("resourceType", resourceType);
          return service.searchResourceEntityByType(client, params);
        }
        public boolean archive() {
          return service.changeResourceEntityStorageState(client, createArchivedStorageRequest(entity));
        }
      };
    }
  }

  public class ResourceTypeAssert extends PersistableEntityAssert<ResourceType> {
    public ResourceTypeAssert(ClientInfo client, ResourceType resourceType) {
      super(client, resourceType);
      this.methods = new EntityServiceMethods() {
        public ResourceType load() {
          return service.getResourceTypeByType(client, entity.getType());
        }
        public ResourceType save(ResourceType resourceType) {
          service.saveResourceType(client, resourceType);
          return load();
        }
        public List<?> searchEntity() {
          return service.searchResourceType(client, createSearchQuery(resourceType.getType()));
        }
        public boolean archive() {
          return service.changeResourceTypeStorageState(client, createArchivedStorageRequest(entity));
        }
      };
    }

    public ResourceTypeAssert testDeleteTag(String resourceEntityCode) {
      ResourceEntity retrieveResourceEntity = service.getResourceEntityByCodeAndResourceType(client, resourceEntityCode, entity.getType());
      AssertTool.assertEquals(1, retrieveResourceEntity.getTags().size());

      ResourceType modified = DataSerializer.JSON.clone(ResourceType.class, entity);

      for(ResourceTag tag : modified.getTags()) {
        tag.markDeleted();
      }
      ResourceType savedEntity = this.methods.save(modified);

      ResourceType loadEntity = this.methods.load();
      AssertTool.assertEquals(0, loadEntity.getTags().size());

      //retrieveResourceEntity = service.getResourceEntityByCodeAndResourceType(client, resourceEntityCode, entity.getType());
      //AssertTool.assertEquals(0, retrieveResourceEntity.getTags().size());
      return this;
    }
  }

}
