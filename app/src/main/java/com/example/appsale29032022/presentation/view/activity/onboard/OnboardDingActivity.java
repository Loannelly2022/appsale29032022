package com.example.appsale29032022.presentation.view.activity.onboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appsale29032022.R;
import com.example.appsale29032022.presentation.view.adapter.OnboardDingPagerAdapter;

public class OnboardDingActivity extends AppCompatActivity {

    TextView tvRequestLogin;
    LinearLayout btnGetStarted;
    ViewPager2 onboardDingViewPager;
    OnboardDingPagerAdapter onboardDingPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        initial();
        // Request Login Text
        setTextRequestLogin();
    }

    private void initial() {
        tvRequestLogin = findViewById(R.id.textview_request_login);
        btnGetStarted = findViewById(R.id.button_get_started);
        onboardDingViewPager = findViewById(R.id.view_pager_onboard_ding);

        onboardDingPagerAdapter = new OnboardDingPagerAdapter(this);
        onboardDingViewPager.setAdapter(onboardDingPagerAdapter);
    }


    private void setTextRequestLogin() {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append("Already Have An Account?");
        int start = builder.length();
        builder.append(" Log In");
        builder.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {

            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                ds.setColor(getResources().getColor(R.color.primary));
            }
        }, start, builder.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tvRequestLogin.setText(builder);
        tvRequestLogin.setHighlightColor(Color.TRANSPARENT);
        tvRequestLogin.setMovementMethod(LinkMovementMethod.getInstance());
    }

}
