package com.example.appsale29032022.common;
import android.content.Context;
import android.graphics.Color;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Patterns;
import java.util.regex.Pattern;
import android.text.SpannableStringBuilder;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.appsale29032022.R;

public class StringCommon {
    public static boolean isValidEmail(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    public static SpannableStringBuilder setClickColorLink(String text, OnListenClick onListenClick, Context context) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(text);
        builder.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                onListenClick.onClick();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                ds.setColor(context.getResources().getColor(R.color.primary));
            }
        }, 0, builder.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        return builder;
    }

    interface OnListenClick {
        void onClick();

    }

}
