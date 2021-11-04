package com.devteam.module.settings.resource.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.devteam.module.data.db.entity.BaseEntity;
import com.devteam.util.ds.Arrays;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
  name = ResourceType.TABLE_NAME,
  uniqueConstraints = {
    @UniqueConstraint(
      name =  ResourceType.TABLE_NAME + "_type",
      columnNames = { "type" }
    )
  },
  indexes = {
    @Index(columnList="type,label")
  }
)
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor @Getter @Setter
public class ResourceType extends BaseEntity<Long> {
  private static final long serialVersionUID = 1L;
  public static final String TABLE_NAME = "vion_settings_resource_type";

  @NotNull
  private String  type;
  private String  label;
  private boolean required;

  private String  description;
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "resource_type_id", referencedColumnName = "id")
  private Set<ResourceTag> tags = new HashSet<>();

  public ResourceType(String type, String label) {
    this.type = type;
    this.label = label;
  }

  public ResourceType withResourceTag(ResourceTag ... tags) {
    this.tags = Arrays.addToSet(this.tags, tags);
    return this;
  }

  public String identify() { return type; }
}
