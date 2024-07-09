package com.lvb.challenge.picpay.PicpayBackendChallenge.entity;

import com.lvb.challenge.picpay.PicpayBackendChallenge.entity.base.BaseEntity;
import com.lvb.challenge.picpay.PicpayBackendChallenge.entity.enums.UserType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class AccountBase extends BaseEntity {

    //For Seller documentNumber is CNPJ
    @Column(unique=true, nullable = false)
    private String userDocument;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(nullable = false)
    private String firstname;

    @Column
    private String lastName;

    @Column(unique=true, nullable = false)
    private String email;

    @Column
    private Boolean emailValidated = false;

    @Column
    private String phoneNumber;

    @Column
    private Boolean phoneNumberValidated = false;

    @Column(unique=true, nullable = false)
    private String userIdKeycloak;

    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
    private AccountBalance accountBalance;

}
