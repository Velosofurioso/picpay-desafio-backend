package com.lvb.challenge.picpay.PicpayBackendChallenge.dto.user;

import com.lvb.challenge.picpay.PicpayBackendChallenge.dto.embeddable.AccountAttributes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {

    @NonNull
    private AccountAttributes accountAttributes;

    @NonNull
    private String cpf;

}
