package com.devteam.module.data.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.devteam.module.util.ds.MapObject;
import com.devteam.module.util.ds.Objects;
import com.devteam.module.util.error.ErrorType;
import com.devteam.module.util.error.RuntimeError;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

@NoArgsConstructor
@Getter @Setter
public class DynamicEntity extends MapObject {

  private static final long serialVersionUID = 1L;

  public Long id() { return getLong("id", null); }

  @JsonIgnore
  public String getEditState() { return getString("editState", null); }

  public <T extends Persistable<Long>> void update(T entity) {
    Objects.assertEquals(id(), entity.getId());
    try {
      for(String field : this.keySet()) {
        updateField(field, entity);
      }
    } catch (IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeError(ErrorType.IllegalArgument, "Cannot map data from dynamic entity to entity", e);
    }
  }

  protected <T extends Persistable<Long>> void updateField(String field, T entity) throws IllegalAccessException, InvocationTargetException {
    if("id".equals(field)) return;
    if("editState".equals(field)) return;
    BeanUtils.setProperty(entity, field, get(field));
  }
}