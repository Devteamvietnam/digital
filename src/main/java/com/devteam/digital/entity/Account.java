package com.devteam.digital.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "account")
@Data
public class Account<AccountPreferenceEntity> {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String username;
    private String email;
    private String password;
    private String fullname;
    private String firstname;
    private String lastname;
    private String phonenumber;
    private String gender;
    private String address;
    private String city;

    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "account_role",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id"))
    private Set<AccountRole> accountRoles = new HashSet<AccountRole>();

    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "account",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private AccountImage image;

}
