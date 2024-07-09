package com.lvb.challenge.picpay.PicpayBackendChallenge.util;

import java.util.Random;

public class Utils {

    public static String getUsernameFromEmail(final String email) {
        return email.split("@")[0];
    }

    public static long generateRandomNumber() {
        Random random = new Random();

        // Generates a random number between 0 and 999999 (with 6 digits)
        long num = random.nextLong();
        num = Math.abs(num % 900000) + 100000;

        return num;
    }

    public static Long parseLong(String str) {
        if(!isNumeric(str))
            return null;

        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
