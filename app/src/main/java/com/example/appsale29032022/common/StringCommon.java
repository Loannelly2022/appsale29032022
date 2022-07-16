package com.example.appsale29032022.common;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Patterns;
import android.view.View;

import androidx.annotation.NonNull;

import com.airbnb.lottie.animation.content.Content;
import com.example.appsale29032022.R;

/**
 * Created by pphat on 7/14/2022.
 */
public class StringCommon {

    public static boolean isValidEmail(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
}
