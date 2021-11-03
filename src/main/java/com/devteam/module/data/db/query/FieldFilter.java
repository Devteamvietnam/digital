package com.devteam.module.data.db.query;

import com.devteam.module.util.error.ErrorType;
import com.devteam.module.util.error.RuntimeError;
import lombok.Getter;
import lombok.Setter;

abstract public class FieldFilter {
  @Getter @Setter
  protected String   table;

  @Getter @Setter
  private String     name;

  @Getter @Setter
  private FilterType filterType = FilterType.NotSet;

  @Getter @Setter
  private String filterOp;

  @Getter @Setter
  private String tableField;

  @Getter @Setter
  private boolean    required = false;


  public FieldFilter() {}

  public FieldFilter(String name) {
    this.name = name;
  }

  public FieldFilter(Class<?> entity, String field, String op) {
    this.name = field;
    this.filterOp = op;
    this.tableField = SqlUtil.createFieldExpression(entity, field);
  }

  protected void assertSameFilter(FieldFilter other) {
    if(!name.equals(other.getName())) {
      throw new RuntimeError(ErrorType.IllegalArgument, name + " is not the same with " + other.getName());
    }
  }
}