package com.devteam.module.settings.location.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.devteam.module.data.db.entity.BaseEntity;
import com.devteam.util.ds.Arrays;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
  name = Location.TABLE_NAME,
  uniqueConstraints = {
    @UniqueConstraint(
     name = Location.TABLE_NAME + "_code",
     columnNames = { "country_code", "state_code", "city_code", "code" }
    )
  },
  indexes = { @Index(columnList = "code,label") }
)
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@Getter @Setter
public class Location extends BaseEntity<Long> implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String TABLE_NAME = "dev_settings_location";

  @NotNull
  private String code;

  private String label;
  private String otherLabel;
  @Column(length = 65536)
  private String address;
  private String district;

  @Column(name = "city_code")
  private String cityCode;

  @Column(name = "state_code")
  private String stateCode;

  @Column(name = "country_code")
  private String countryCode;

  @Column(name = "postal_code")
  private String postalCode;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "location_id", referencedColumnName = "id")
  private List<LocationAlias> aliases = new ArrayList<>();

  @ManyToMany(cascade = { CascadeType.MERGE })
  @JoinTable(
    name = "settings_location_type_rel",
    joinColumns = @JoinColumn(name = "location_id"), inverseJoinColumns = @JoinColumn(name = "location_type_id")
  )
  private List<LocationType> locationTypes;

  public Location(String code, String label) {
    this.code = code;
    this.label = label;
  }

  public Location withCode(String name) {
    setCode(name);
    if(label == null) label = name;
    return this;
  }

  public Location withLabel(String label) {
    setLabel(label);
    return this;
  }

  public Location withCountry(String countryCode) {
    this.countryCode = countryCode;
    return this;
  }

  public Location withCountry(Country country) {
    this.countryCode = country.getCode();
    return this;
  }

  public Location withState(State state) {
    this.stateCode = state.getCode();
    return this;
  }

  public Location withCity(City city) {
    this.cityCode = city.getCode();
    return this;
  }

  public Location withLocationType(List<LocationType> locationTypes) {
    this.locationTypes = locationTypes;
    return this;
  }

  public Location withAliases(LocationAlias... alias) {
    aliases = Arrays.addToList(aliases, alias);
    return this;
  }
}
