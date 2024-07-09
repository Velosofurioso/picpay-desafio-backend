package com.lvb.challenge.picpay.PicpayBackendChallenge.dto.account.transfer;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateTransferConfirmationDTO implements Serializable {

    private String status;
    private AuthorizationData data;

    @lombok.Data
    public static class AuthorizationData {
        private boolean authorization;
    }

}


