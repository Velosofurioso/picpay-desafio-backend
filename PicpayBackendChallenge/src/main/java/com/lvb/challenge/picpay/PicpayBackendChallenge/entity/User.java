package com.lvb.challenge.picpay.PicpayBackendChallenge.entity;

import com.lvb.challenge.picpay.PicpayBackendChallenge.entity.base.BaseEntity;
import com.lvb.challenge.picpay.PicpayBackendChallenge.entity.embeddable.AccountAttributes;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    private AccountAttributes accountAttributes;

    @Column(unique=true, nullable = false)
    private String cpf;

    //TODO considerar remover ese campo da entidade com a autenticacao com keycloak
    @Column(nullable = false)
    private String password;

}
