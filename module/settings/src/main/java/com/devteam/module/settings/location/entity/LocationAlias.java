package com.devteam.module.settings.location.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.devteam.module.data.db.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(
  name = LocationAlias.TABLE_NAME
)
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@Getter @Setter
public class LocationAlias extends BaseEntity<Long> implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String TABLE_NAME = "settings_location_reference_code";

  @NotNull
  private String code;

  @NotNull
  private String type;

  private String label;

  private String creator;

  private String description;

  public LocationAlias(String code, String type , String label) {
    this.code = code;
    this.type = type;
    this.label = label;
  }
}