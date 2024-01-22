package com.lvb.challenge.picpay.PicpayBackendChallenge.util;

public class Utils {

    public static String getUsernameFromEmail(final String email) {
        return email.split("@")[0];
    }
}
