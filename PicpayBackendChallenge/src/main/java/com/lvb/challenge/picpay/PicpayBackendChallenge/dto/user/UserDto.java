package com.lvb.challenge.picpay.PicpayBackendChallenge.dto.user;

import com.lvb.challenge.picpay.PicpayBackendChallenge.entity.enums.UserType;
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
    private String userDocument;

    @NonNull
    private UserType userType;

    @NonNull
    private String firstname;

    private String lastName;

    @NonNull
    private String email;

    private String phoneNumber;
}
