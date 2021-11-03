package com.devteam.module.account.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.devteam.module.util.text.DateUtil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(
  name = UserProfile.TABLE_NAME,
  indexes = {
    @Index(columnList="login_id"),
  }
)
@JsonInclude(Include.NON_NULL)
@Getter @Setter
public class UserProfile extends BaseProfile {
  public static final String TABLE_NAME = "devteam_account_user_profile";

  @Transient
  final private AccountType  accountType = AccountType.USER;

  @Column(name = "first_name")
  private String            firstName;
  @Column(name = "last_name")
  private String            lastName;
  @Column(name = "full_name")
  private String            fullName;

  @Column(name = "gender_status")
  private GenderStatus gender = GenderStatus.Male;

  @JsonFormat(pattern = DateUtil.COMPACT_DATETIME_FORMAT)
  private Date   birthday = new Date();

  private float  height;

  private float  weight;

  @Column(name = "personal_id")
  private String personalId;

  @Column(name = "marital_status")
  private MarialStatus maritalStatus = MarialStatus.Single;

  private String hobby;

  private String nickname;

  public UserProfile() { }

  public UserProfile(String loginId) {
    this.loginId = loginId;
  }

  public UserProfile(String loginId, String fullName, String email) {
    this.loginId = loginId;
    this.fullName = fullName;
    this.email = email;
  }

  public UserProfile withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public UserProfile withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public UserProfile withEmail(String email) {
    setEmail(email);
    return this;
  }

  public UserProfile  withFullName(String fullName) {
    setFullName(fullName);
    return this;
  }
  public UserProfile  withMobile(String mobile) {
    setMobile(mobile);
    return this;
  }

  public UserProfile withGender(GenderStatus gender) {
    this.gender = gender;
    return this;
  }

  public UserProfile withBirthday(Date birthday) {
    this.birthday = birthday;
    return this;
  }


  public UserProfile withAvatar(String avatar) {
    this.avatarUrl = avatar;
    return this;
  }

  public UserProfile withHeight(float height) {
    this.height = height;
    return this;
  }

  public UserProfile withWeight(float weight) {
    this.weight = weight;
    return this;
  }

  public UserProfile withPersonalId(String personalId) {
    this.personalId = personalId;
    return this;
  }

  public UserProfile withMaritalStatus(MarialStatus maritalStatus) {
    this.maritalStatus = maritalStatus;
    return this;
  }

  public UserProfile withHobby(String hobby) {
    this.hobby = hobby;
    return this;
  }

  public UserProfile withNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }
}
