package com.example.appsale29032022.presentation.view.activity.onboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.BaseMenuPresenter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Color;
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
import com.example.appsale29032022.common.AppConstant;
import com.example.appsale29032022.common.SpannedCommon;
import com.example.appsale29032022.data.local.AppCache;
import com.example.appsale29032022.presentation.view.activity.sign_in.SignInActivity;
import com.example.appsale29032022.presentation.view.activity.splash.SplashActivity;
import com.example.appsale29032022.presentation.view.adapter.OnboardDingPagerAdapter;

public class OnboardDingActivity extends AppCompatActivity{
    TextView tvRequestLogin;
    LinearLayout btnGetStarted;
    ViewPager2 onboardDingViewPager;
    OnboardDingPagerAdapter onboardDingPagerAdapter;
    private BaseMenuPresenter view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        initial();
        event();
        // Request Login Text
        setTextRequestLogin();
        // ViewPager OnboardDing
    }
    private void event() {
        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                navigateLoginScreen();
            }
        });
    }
        private void navigateLoginScreen () {
                AppCache.getInstance(OnboardDingActivity.this)
                        .setValue(AppConstant.ONBOARD_DING_FIRST_TIME_DISPLAY_KEY, true);
                        commit();
                Intent intent = new Intent(this, SignInActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.alpha_fade_in, R.anim.alpha_fade_out);
            }
            private void commit () {
            }
            private void initial () {
                tvRequestLogin = findViewById(R.id.textview_request_login);
                btnGetStarted = findViewById(R.id.button_get_started);
                onboardDingViewPager = findViewById(R.id.view_pager_onboardding);

                onboardDingPagerAdapter = new OnboardDingPagerAdapter(this);
                onboardDingViewPager.setAdapter(onboardDingPagerAdapter);
            }

            private void setTextRequestLogin () {
                SpannableStringBuilder builder = new SpannableStringBuilder();
                builder.append("Already Have An Account?");
                builder.append(SpannedCommon.setClickColorLink("Login", this, () -> navigateLoginScreen()));

                tvRequestLogin.setText(builder);
                tvRequestLogin.setHighlightColor(Color.TRANSPARENT);
                tvRequestLogin.setMovementMethod(LinkMovementMethod.getInstance());

            }
        }

