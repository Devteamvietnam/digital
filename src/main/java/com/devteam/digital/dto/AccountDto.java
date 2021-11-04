package com.devteam.digital.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@JsonInclude(Include.NON_NULL)
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private String id;
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
    private String email;
    @NotBlank
    @Size(max = 120)
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
    private String fullname;
    private String firstname;
    private String lastname;
    private String phonenumber;
    private String gender;
    private String address;
    private String city;

    @EqualsAndHashCode.Exclude
    private Set<String> roles = new HashSet<String>();

    @EqualsAndHashCode.Exclude
    private AccountImage image;

}
