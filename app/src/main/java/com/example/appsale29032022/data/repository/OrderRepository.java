package com.example.appsale29032022.data.repository;

import android.content.Context;

import com.example.appsale29032022.data.remote.ApiService;
import com.example.appsale29032022.data.remote.RetrofitClient;
import com.example.appsale29032022.data.remote.dto.AppResource;
import com.example.appsale29032022.data.remote.dto.OrderDTO;

import java.util.HashMap;

import retrofit2.Call;

public class OrderRepository {
    private ApiService apiService;

    public OrderRepository() {
        apiService = RetrofitClient.getInstance().getApiService();
    }

    public Call<AppResource<OrderDTO>> addToCart(String idFood) {
        HashMap<String,String> body = new HashMap<>();
        body.put("id_product",idFood);
        return apiService.addToCart(body);
    }
}
