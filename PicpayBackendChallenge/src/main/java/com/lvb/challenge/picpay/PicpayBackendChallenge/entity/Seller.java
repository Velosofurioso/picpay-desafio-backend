package com.lvb.challenge.picpay.PicpayBackendChallenge.entity;

import com.lvb.challenge.picpay.PicpayBackendChallenge.entity.enums.UserType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class Seller extends AccountBase {

    public Seller () {
        super();
        this.setUserType(UserType.SELLER);
    }

}
