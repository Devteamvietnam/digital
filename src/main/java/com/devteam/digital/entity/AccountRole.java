package com.devteam.digital.entity;

import com.devteam.digital.dto.ERole;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "account_role")
public class AccountRole {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;
}
