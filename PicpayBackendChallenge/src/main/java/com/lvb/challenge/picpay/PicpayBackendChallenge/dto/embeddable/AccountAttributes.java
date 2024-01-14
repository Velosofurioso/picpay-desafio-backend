package com.lvb.challenge.picpay.PicpayBackendChallenge.dto.embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountAttributes implements Serializable {

    private String name;

    private String email;

}
