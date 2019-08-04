package com.walter.cojal.easylearning.utility;

import android.util.Patterns;

public class Util {

    public static Boolean verifyEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
