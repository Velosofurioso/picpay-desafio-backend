package com.lvb.challenge.picpay.PicpayBackendChallenge.entity.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class AccountAttributes {

    @Column(nullable = false)
    private String name;

    @Column(unique=true, nullable = false)
    private String email;

    @Column(nullable = true)
    private String phoneNumber;
}
