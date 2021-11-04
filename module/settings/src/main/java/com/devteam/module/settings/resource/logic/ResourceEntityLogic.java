package com.devteam.module.settings.resource.logic;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.DAOService;
import com.devteam.module.data.db.entity.ChangeStorageStateRequest;
import com.devteam.module.data.db.query.ClauseFilter;
import com.devteam.module.data.db.query.EntityTable;
import com.devteam.module.data.db.query.OptionFilter;
import com.devteam.module.data.db.query.RangeFilter;
import com.devteam.module.data.db.query.SearchFilter;
import com.devteam.module.data.db.query.SqlQuery;
import com.devteam.module.data.db.query.SqlQueryParams;
import com.devteam.module.settings.resource.entity.ResourceEntity;
import com.devteam.module.settings.resource.repository.ResourceEntityRepository;

@Component
public class ResourceEntityLogic extends DAOService {

  @Autowired
  private ResourceEntityRepository          resourceEntityRepo;


  public ResourceEntity getByCodeAndResourceType(ClientInfo client, String code, String resourceType) {
    return resourceEntityRepo.getByCodeAndResourceType(code, resourceType);
  }

  public ResourceEntity save(ClientInfo client, ResourceEntity resourceEntity) {
    resourceEntity.set(client);
    return resourceEntityRepo.save(resourceEntity);
  }

  public Boolean delete(ClientInfo client, Long ... ids) {
    resourceEntityRepo.deleteAllById(Arrays.asList(ids));
    return true;
  }

  public List<ResourceEntity> searchResourceEntityByType(ClientInfo client, SqlQueryParams params) {
    String[] SEARCH_FIELDS = new String[]{"label", "code", "resourceType"};
    SqlQuery query = new SqlQuery()
        .ADD_TABLE(new EntityTable(ResourceEntity.class).selectAllFields())
        .FILTER(SearchFilter.isearch(ResourceEntity.class, SEARCH_FIELDS))
        .FILTER(new ClauseFilter(ResourceEntity.class, "resourceType", "=", ":resourceType"))
        .FILTER(
            OptionFilter.storageState(ResourceEntity.class),
            RangeFilter.createdTime(ResourceEntity.class),
            RangeFilter.modifiedTime(ResourceEntity.class))
        .ORDERBY(new String[] {"label", "resourceType"}, "resourceType", "ASC");
    return query(client, query, params, ResourceEntity.class);
  }

  public boolean changeStorageState(ClientInfo client, ChangeStorageStateRequest req) {
    resourceEntityRepo.setResourceEntityState(req.getNewStorageState(), req.getEntityIds());
    return true;
  }
}
