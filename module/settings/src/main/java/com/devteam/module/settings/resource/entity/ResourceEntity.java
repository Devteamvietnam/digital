package com.devteam.module.settings.resource.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
    name = ResourceEntity.TABLE_NAME,
    uniqueConstraints = {
      @UniqueConstraint(
         name = ResourceEntity.TABLE_NAME + "_code",
         columnNames = { "code", "resource_type" }
      )
    },
    indexes = {
      @Index(columnList="resource_type, code")
    }
  )
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor @Getter @Setter
public class ResourceEntity extends BaseEntity<Long> {
  private static final long serialVersionUID = 1L;
  public static final String TABLE_NAME = "vion_settings_resource_entity";

  @NotNull
  @Column(name = "resource_type")
  private String resourceType;

  @NotNull
  private String code;

  private String label;

  private String description;


  @ManyToMany(cascade = { CascadeType.MERGE})
  @JoinTable(
    name = "settings_resource_tag_rel",
    joinColumns = @JoinColumn(name = "resource_entity_id"),
    inverseJoinColumns = @JoinColumn(name = "resource_tag_id")
  )
  private Set<ResourceTag> tags = new HashSet<>();;

  public ResourceEntity(String name, String label) {
    this.code = name;
    this.label = label;
  }

  public ResourceEntity withType(String type) {
    this.resourceType = type;
    return this;
  }

  public ResourceEntity withTag(ResourceTag ... tag) {
    this.tags = Arrays.addToSet(this.tags, tag);
    return this;
  }

  public String identify() { return code; }
}
