package com.lvb.challenge.picpay.PicpayBackendChallenge.dto.user;

import lombok.Data;
import lombok.NonNull;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto extends UserDto implements Serializable {

    @NonNull
    private String password;

}
