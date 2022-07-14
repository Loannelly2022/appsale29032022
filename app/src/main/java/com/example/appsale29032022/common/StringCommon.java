package com.example.appsale29032022.common;

import android.text.TextUtils;
import android.util.Patterns;

/**
 * Created by pphat on 7/14/2022.
 */
public class StringCommon {

    public static boolean isValidEmail(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
}
