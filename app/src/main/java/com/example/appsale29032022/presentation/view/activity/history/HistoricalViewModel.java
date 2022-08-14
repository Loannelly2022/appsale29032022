package com.example.appsale29032022.presentation.view.activity.history;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.appsale29032022.data.model.Order;
import com.example.appsale29032022.data.remote.dto.AppResource;
import com.example.appsale29032022.data.remote.dto.OrderDTO;
import com.example.appsale29032022.data.repository.HistoricalRepository;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoricalViewModel extends ViewModel {
    private final HistoricalRepository historicalRepository;
    private MutableLiveData<AppResource<List<Order>>> historicalData = new MutableLiveData<>();

    public HistoricalViewModel(Context context){
        historicalRepository = new HistoricalRepository(context);
        if(historicalData == null){
            historicalData = new MutableLiveData<>();
        }
    }
    public LiveData<AppResource<List<Order>>> getHistory(){return historicalData;}
    public void fetchHistory(){
        historicalData.setValue(new AppResource.Loading<>(null));
        Call<AppResource<List<OrderDTO>>> callHistory = historicalRepository.fetchHistory();
        callHistory.enqueue(new Callback<AppResource<List<OrderDTO>>>() {
            @Override
            public void onResponse(Call<AppResource<List<OrderDTO>>> call, Response<AppResource<List<OrderDTO>>> response) {
                if (response.isSuccessful()) {
                    AppResource<List<OrderDTO>> historyResponce = response.body();

                    if (historyResponce.data != null) {
                        List<Order> listOrder = new ArrayList<>();
                        for (OrderDTO orderDTO : historyResponce.data) {
                            listOrder.add(
                                    new Order(orderDTO.getId(),
                                            orderDTO.getFoods(),
                                            orderDTO.getIdUser(),
                                            orderDTO.getPrice(),
                                            orderDTO.getStatus(),
                                            orderDTO.getDate_created())
                            );
                        }
                        historicalData.setValue(new AppResource.Success<>(listOrder));
                    }
                } else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String message = jsonObject.getString("message");
                        historicalData.setValue(new AppResource.Error<>(message));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<AppResource<List<OrderDTO>>> call, Throwable t) {
                historicalData.setValue(new AppResource.Error<>(t.getMessage()));
            }
        });
    }
}