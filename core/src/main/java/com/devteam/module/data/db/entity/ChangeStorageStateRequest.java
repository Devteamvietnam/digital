package com.devteam.module.data.db.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.devteam.module.enums.StorageState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
public class ChangeStorageStateRequest {
  @Enumerated(EnumType.STRING)
  private StorageState newStorageState;
  private List<Long>  entityIds;

  public ChangeStorageStateRequest(StorageState newState) {
    this.newStorageState = newState;
  }

  public ChangeStorageStateRequest withEntityId(Long id) {
    if(entityIds == null) entityIds = new ArrayList<>();
    entityIds.add(id);
    return this;
  }
}
