package com.devteam.module.settings.location.entity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.devteam.module.data.db.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
  name = LocationType.TABLE_NAME,
  uniqueConstraints = {
    @UniqueConstraint(
      name = LocationType.TABLE_NAME + "_type",
      columnNames = { "type" }),
  },
  indexes = { @Index(columnList="type") }
)
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@Getter @Setter
public class LocationType extends BaseEntity<Long> {
  public static final String TABLE_NAME = "vion_settings_location_type";

  @NotNull
  private String type;

  @NotNull
  private String label;

  public LocationType(String label, String type) {
    this.label = label;
    this.type = type;
  }

  public LocationType withLabel(String label) {
    setLabel(label);
    return this;
  }

  public LocationType withType(String type) {
    setType(type);
    return this;
  }
}
