package com.lvb.challenge.picpay.PicpayBackendChallenge.dto.user;

import lombok.*;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto extends UserDto implements Serializable {

    @NonNull
    String password;

}
