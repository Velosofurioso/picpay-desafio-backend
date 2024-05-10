package com.lvb.challenge.picpay.PicpayBackendChallenge.dto.seller;

import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateSellerDto extends SellerDto implements Serializable {

    @NonNull
    String password;

}
