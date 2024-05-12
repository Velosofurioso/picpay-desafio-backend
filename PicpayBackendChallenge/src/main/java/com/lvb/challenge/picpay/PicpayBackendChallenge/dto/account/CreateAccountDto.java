package com.lvb.challenge.picpay.PicpayBackendChallenge.dto.account;

import com.lvb.challenge.picpay.PicpayBackendChallenge.dto.user.UserDto;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccountDto extends AccountDto {

    @NonNull
    private String password;

}
