package com.lvb.challenge.picpay.PicpayBackendChallenge.dto.seller;

import com.lvb.challenge.picpay.PicpayBackendChallenge.dto.embeddable.AccountAttributes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerDto implements Serializable {

    @NonNull
    private AccountAttributes accountAttributes;

    @NonNull
    private String cnpj;
}
