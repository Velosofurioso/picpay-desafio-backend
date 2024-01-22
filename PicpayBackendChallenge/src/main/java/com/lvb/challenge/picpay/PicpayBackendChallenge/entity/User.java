package com.lvb.challenge.picpay.PicpayBackendChallenge.entity;

import com.lvb.challenge.picpay.PicpayBackendChallenge.entity.base.BaseEntity;
import com.lvb.challenge.picpay.PicpayBackendChallenge.entity.enums.UserType;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    //CPF or CNPJ
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
    private String phoneNumber;

    @Column(unique=true, nullable = false)
    private String userIdKeycloak;

}
