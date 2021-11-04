package com.devteam.module.settings.location.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import com.devteam.module.data.db.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter @Setter
public class AbstractContact extends BaseEntity<Long> {
  private static final long serialVersionUID = 1L;
  @NotNull
  protected String label;
  @Column(length = 65536)
  protected String address;
  protected String district;

  @Column(name = "city_name")
  protected String cityName;

  @Column(name = "city_label")
  protected String cityLabel;

  @Column(name = "state_name")
  protected String stateName;

  @Column(name = "state_label")
  protected String stateLabel;

  @Column(name = "country_name")
  protected String countryName;

  @Column(name = "country_label")
  protected String countryLabel;

  @Column(name = "postal_code")
  protected String postalCode;

  @Convert(converter = StringSetConverter.class)
  protected Set<String> email;

  @Convert(converter = StringSetConverter.class)
  protected Set<String> phone;

  @Convert(converter = StringSetConverter.class)
  protected Set<String> mobile;

  @Convert(converter = StringSetConverter.class)
  protected Set<String> fax;

  @Convert(converter = StringSetConverter.class)
  protected Set<String> website;

  public AbstractContact withLabel(String label) {
    this.label = label;
    return this;
  }

  public AbstractContact withAddress(String addr) {
    this.address = addr;
    return this;
  }

  public AbstractContact withDistrict(String district) {
    this.district = district;
    return this;
  }

  public AbstractContact withCity(String city) {
    this.cityName = city;
    this.cityLabel = city;
    return this;
  }

  public AbstractContact withCountry(String country) {
    this.countryName = country;
    this.countryLabel = country;
    return this;
  }

  public AbstractContact withMobile(String mobile) {
    if(this.mobile == null) this.mobile = new HashSet<>();
    this.mobile.add(mobile);
    return this;
  }

  public AbstractContact withEmail(String email) {
    if(this.email == null) this.email = new HashSet<>();
    this.email.add(email);
    return this;
  }
}
