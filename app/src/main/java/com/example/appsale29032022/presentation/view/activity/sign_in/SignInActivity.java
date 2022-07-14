package com.example.appsale29032022.presentation.view.activity.sign_in;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.appsale29032022.R;

public class SignInActivity extends AppCompatActivity {

    SignInViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initial();
        event();
    }

    private void event() {
        viewModel.signIn("demo1@gmail.com", "123456789");
    }

    private void initial() {
        viewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new SignInViewModel();
            }
        }).get(SignInViewModel.class);
    }
}
