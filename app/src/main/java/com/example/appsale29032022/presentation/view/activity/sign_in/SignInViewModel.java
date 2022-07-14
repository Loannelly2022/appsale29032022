package com.example.appsale29032022.presentation.view.activity.sign_in;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appsale29032022.data.remote.dto.AppResource;
import com.example.appsale29032022.data.remote.dto.UserDTO;
import com.example.appsale29032022.data.repository.AuthenticationRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by pphat on 7/14/2022.
 */
public class SignInViewModel extends ViewModel {
    private final AuthenticationRepository authenticationRepository;

    public SignInViewModel() {
        authenticationRepository = new AuthenticationRepository();
    }

    public void signIn(String email, String password) {
        authenticationRepository
                .signIn(email, password)
                .enqueue(new Callback<AppResource<UserDTO>>() {
                    @Override
                    public void onResponse(Call<AppResource<UserDTO>> call, Response<AppResource<UserDTO>> response) {
                        AppResource<UserDTO> resourceUserDTO = response.body();
                        if (resourceUserDTO != null) {
                            Log.d("BBB", resourceUserDTO.data.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<AppResource<UserDTO>> call, Throwable t) {

                    }
                });
    }
}
