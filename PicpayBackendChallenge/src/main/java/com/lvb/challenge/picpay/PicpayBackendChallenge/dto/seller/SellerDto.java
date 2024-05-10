package com.lvb.challenge.picpay.PicpayBackendChallenge.dto.seller;

import com.lvb.challenge.picpay.PicpayBackendChallenge.dto.account.AccountDto;
import com.lvb.challenge.picpay.PicpayBackendChallenge.entity.enums.UserType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class SellerDto extends AccountDto implements Serializable {

    public SellerDto() {
        super();
        this.setUserType(UserType.SELLER);
    }
}
