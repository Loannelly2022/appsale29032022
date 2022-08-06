package com.example.appsale29032022.data.repository;

import android.content.Context;
import com.example.appsale29032022.data.remote.ApiService;
import com.example.appsale29032022.data.remote.RetrofitClient;
import com.example.appsale29032022.data.remote.dto.AppResource;
import com.example.appsale29032022.data.remote.dto.UserDTO;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;

public class AuthenticationRepository {
    private ApiService apiService;

    public AuthenticationRepository() {
        apiService = RetrofitClient.getInstance().getApiService();
    }

    public Call <AppResource <UserDTO>> signIn (String email, String password) {
        HashMap<String, Object> map = new HashMap<>();
        map.put ("email", email);
        map.put ("password", password);

        return apiService.signIn(map);
    }
}
