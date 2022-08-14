package com.example.appsale29032022.data.repository;

import android.content.Context;
import com.example.appsale29032022.data.remote.ApiService;
import com.example.appsale29032022.data.remote.RetrofitClient;
import com.example.appsale29032022.data.remote.dto.AppResource;
import com.example.appsale29032022.data.remote.dto.OrderDTO;
import org.json.JSONObject;
import java.util.List;
import retrofit2.Call;

public class HistoricalRepository {
    private ApiService apiService;

    public HistoricalRepository(Context context) {
        apiService = RetrofitClient.getInstance(context).getApiService();

    }
    public Call<AppResource<List<OrderDTO>>> fetchHistory() {
        JSONObject jsonObject = new JSONObject();
        return apiService.orderHistory(jsonObject);

    }
}
