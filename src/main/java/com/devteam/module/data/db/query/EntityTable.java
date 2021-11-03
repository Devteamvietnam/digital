package com.devteam.module.data.db.query;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.devteam.module.util.bean.BeanInspector;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonInclude(Include.NON_NULL)
public class EntityTable {

  @Getter @Setter
  private String table;

  @Getter @Setter
  private Class<?> entity ;

  @Getter
  private List<SelectField> selectFields ;

  public <T>  EntityTable(Class<T> entity) {
    this.entity = entity;
    this.table = entity.getDeclaredAnnotation(Table.class).name();
  }

  public EntityTable(String tableName) {
    this.table = tableName;
  }

  public EntityTable addSelectField(String fieldName, String alias) {
    return addSelectField(null, fieldName, alias);
  }

  public EntityTable addSelectField(SqlFunction func, String fieldName, String alias) {
    if(Objects.isNull(selectFields)) {
      selectFields = new ArrayList<>();
    }
    String fieldExpression = null;
    if(entity == null) {
      fieldExpression =  SqlUtil.createFieldExpression(table, fieldName);
    } else {
      fieldExpression = SqlUtil.createFieldExpression(entity, fieldName);
    }
    selectFields.add(new SelectField(func, fieldExpression, alias));
    return this;
  }

  public EntityTable selectAllFields() {
    if(Objects.isNull(selectFields)) {
      selectFields = new ArrayList<>();
    }
    BeanInspector<?> inspector = BeanInspector.get(entity);
    for(PropertyDescriptor sel : inspector.getPropertyDescriptors()) {
      if(!inspector.isSqlType(sel)) continue;
      SelectField field = SqlUtil.createSelectField(inspector, sel.getName());
      if(field != null) selectFields.add(field);
    }
    return this;
  }
}