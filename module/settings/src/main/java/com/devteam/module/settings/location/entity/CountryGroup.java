package com.devteam.module.settings.location.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.devteam.module.data.db.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(
  name = CountryGroup.TABLE_NAME,
  indexes = { @Index(columnList="name") }
)
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@Getter @Setter
public class CountryGroup extends BaseEntity<Long> implements Serializable {
  private static final long serialVersionUID = 1L;
  public static final String TABLE_NAME = "settings_country_group";

  @Column(name = "parent_id")
  private Long parentId;

  @NotNull
  private String name;

  @NotNull
  private String label;

  @Transient
  private CountryGroup parent;

  public CountryGroup(String label, String name) {
    this.name  = name;
    this.label = label;
  }

  public CountryGroup withParent(CountryGroup parent) {
    if(parent != null) parentId = parent.getId();
    else parentId = null;
    this.parent = parent;
    return this;
  }
}
