package com.devteam.module.settings.resource.logic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.DAOService;
import com.devteam.module.data.db.entity.ChangeStorageStateRequest;
import com.devteam.module.enums.EditState;
import com.devteam.module.data.db.query.EntityTable;
import com.devteam.module.data.db.query.OptionFilter;
import com.devteam.module.data.db.query.RangeFilter;
import com.devteam.module.data.db.query.SearchFilter;
import com.devteam.module.data.db.query.SqlQuery;
import com.devteam.module.data.db.query.SqlQueryParams;
import com.devteam.module.settings.resource.entity.ResourceTag;
import com.devteam.module.settings.resource.entity.ResourceType;
import com.devteam.module.settings.resource.repository.ResourceTagRepository;
import com.devteam.module.settings.resource.repository.ResourceTypeRepository;

@Component
public class ResourceTypeLogic extends DAOService {

  @Autowired
  private ResourceTypeRepository        resourceTypeRepo;

  @Autowired
  private ResourceTagRepository resourceTagRepo;

  public ResourceTag saveResourceTag(ClientInfo client, ResourceTag resourceTag) {
    resourceTag.set(client);
    return resourceTagRepo.save(resourceTag);
  }

  public ResourceTag getResourceTag(ClientInfo client, String resourceTagName) {
    return resourceTagRepo.getByName(resourceTagName);
  }

  public ResourceType getByType(ClientInfo client, String type) {
    return resourceTypeRepo.getByType(type);
  }

  public ResourceType save(ClientInfo client, ResourceType resourceType) {
    Iterator<ResourceTag> i = resourceType.getTags().iterator();
    Set<Long> deleteResourceTags = new HashSet<>();
    while(i.hasNext()) {
      ResourceTag tag = i.next();
      if(tag.getEditState() == EditState.DELETED) {
        deleteResourceTags.add(tag.getId());
        i.remove();
      }
    }
    if(deleteResourceTags.size() > 0) {
      String SQL = "DELETE FROM settings_resource_tag_rel WHERE resource_tag_id IN (:ids)";
      Map<String, Object> params = new HashMap<>();
      params.put("ids", deleteResourceTags);
      int count = update(client,SQL, params);
    }
    resourceType.set(client);
    return resourceTypeRepo.save(resourceType);
  }

  public List<ResourceType> searchType(ClientInfo client, SqlQueryParams params) {
    String[] SEARCH_FIELDS = new String[]{"type", "label"};

    SqlQuery query = new SqlQuery()
        .ADD_TABLE(new EntityTable(ResourceType.class).selectAllFields())
        .FILTER(SearchFilter.isearch(ResourceType.class, SEARCH_FIELDS))
        .FILTER(
            OptionFilter.storageState(ResourceType.class),
            RangeFilter.createdTime(ResourceType.class),
            RangeFilter.modifiedTime(ResourceType.class))
        .ORDERBY(new String[] {"type", "label"}, "type", "ASC");;

    return query(client, query, params, ResourceType.class);
  }

  public boolean changeStorageState(ClientInfo client, ChangeStorageStateRequest req) {
    resourceTypeRepo.setResourceTypeState(req.getNewStorageState(), req.getEntityIds());
    return true;
  }
}
