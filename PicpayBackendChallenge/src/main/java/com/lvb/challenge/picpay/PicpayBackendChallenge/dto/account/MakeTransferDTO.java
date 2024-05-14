package com.lvb.challenge.picpay.PicpayBackendChallenge.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MakeTransferDTO implements Serializable {

    private BigDecimal transferValue;

    private Long payer;

    private Long payee;

}
