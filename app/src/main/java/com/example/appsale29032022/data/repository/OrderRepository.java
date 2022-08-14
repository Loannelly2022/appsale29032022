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
    public OrderRepository(Context context) {
        apiService = RetrofitClient.getInstance(context).getApiService();
    }

    public Call<AppResource<OrderDTO>> addToCart(String idFood) {
        HashMap<String,String> body = new HashMap<>();
        body.put("id_product",idFood);
        return apiService.addToCart(body);

    }
    public Call<AppResource<OrderDTO>> fetchCartOrder() {
        return apiService.fetchCartOrder();

    }
    public Call<AppResource<OrderDTO>> updateCart(String idFood, String idCart, String quantity) {
        HashMap<String,String> body = new HashMap<>();
        body.put("id_product",idFood);
        body.put("id_cart",idCart);
        body.put("quantity",quantity);
        return apiService.uppdateCart(body);

    }
    public Call<AppResource<OrderDTO>> confirmCart(String idCart){
        HashMap<String,String> body = new HashMap<>();
        body.put("id_cart",idCart);
        body.put("status","true");
        return apiService.confirmCart(body);

    }
}