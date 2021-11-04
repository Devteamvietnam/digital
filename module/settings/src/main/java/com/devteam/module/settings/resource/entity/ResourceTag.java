package com.devteam.module.settings.resource.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.devteam.module.data.db.entity.EntityTag;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
  name = ResourceTag.TABLE_NAME,
  uniqueConstraints = {
    @UniqueConstraint(
      name = ResourceTag.TABLE_NAME + "_name",
      columnNames = {"name"}),
  },
  indexes = {
    @Index(columnList= "name"),
  }
)
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@Getter
@Setter
public class ResourceTag extends EntityTag {
  public static final String TABLE_NAME = "vion_settings_resource_tag";

  @Column(name = "resource_type_id", updatable=false, insertable=false)
  private Long resourceTypeId;

  public ResourceTag(String name, String label) {
    super(name, label);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj.getClass() != this.getClass()) {
      return false;
    }
    ResourceTag resourceTag = (ResourceTag) obj;
    if(!this.getName().equals(resourceTag.getName())) {
      return false;
    }

    return true;
  }
}
