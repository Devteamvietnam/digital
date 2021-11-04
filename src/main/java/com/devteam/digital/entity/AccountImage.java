package com.devteam.digital.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString.Exclude;

@Entity
@Table(name = "account_image")
@Data
public class AccountImage {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String fileName;
    private String fileType;

    @Exclude
    @EqualsAndHashCode.Exclude
    @Lob
    private byte[] data;

    @Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne
    @JoinColumn(name="account_id", nullable = false)
    Account account;
}
