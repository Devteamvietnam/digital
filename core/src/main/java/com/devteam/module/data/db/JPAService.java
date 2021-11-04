package com.devteam.module.data.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.EntityType;

import org.springframework.stereotype.Service;

import com.devteam.module.common.ClientInfo;

import lombok.Getter;

@Service
public class JPAService {
  @Getter
  @PersistenceContext(unitName = "entityManagerFactory")
  private EntityManager entityManager;

  public List<EntityInfo> getEntityInfos(ClientInfo client) {
    Set<EntityType<?>> set = entityManager.getMetamodel().getEntities();
    List<EntityInfo> holder = new ArrayList<>();
    for(EntityType<?> sel : set) {
      holder.add(new EntityInfo(sel));
    }
    return holder;
  }
}
