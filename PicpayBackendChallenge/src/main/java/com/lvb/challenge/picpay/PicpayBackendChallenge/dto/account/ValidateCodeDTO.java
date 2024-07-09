package com.lvb.challenge.picpay.PicpayBackendChallenge.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateCodeDTO implements Serializable {

    private String code;
}
