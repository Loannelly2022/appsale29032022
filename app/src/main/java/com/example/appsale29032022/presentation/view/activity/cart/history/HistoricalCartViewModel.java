package com.example.appsale29032022.presentation.view.activity.cart.history;
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
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoricalCartViewModel extends ViewModel {
    private final HistoricalRepository historicalRepository;
    private MutableLiveData<AppResource<Order>> orderData = new MutableLiveData<>();
    private int position;
    public HistoricalCartViewModel(Context context , int position) {
        historicalRepository = new HistoricalRepository(context);
        if (orderData == null) {
            orderData = new MutableLiveData<>();
        }
        this.position = position;
    }
    public LiveData<AppResource<Order>> getOrder() { return orderData;}
    public void fetchCartOrderHistory() {
        orderData.setValue(new AppResource.Loading(null));
        Call<AppResource<List<OrderDTO>>> callHistory = historicalRepository.fetchHistory();
        callHistory.enqueue(new Callback<AppResource<List<OrderDTO>>>() {
            @Override
            public void onResponse(Call<AppResource<List<OrderDTO>>> call, Response<AppResource<List<OrderDTO>>> response) {
                if (response.isSuccessful()) {
                    AppResource<List<OrderDTO>> historicalResponce = response.body();
                    if (historicalResponce.data != null) {
                        orderData.setValue(new AppResource.Success<>(new Order(historicalResponce.data.get(position).getId(),
                                historicalResponce.data.get(position).getFoods(),
                                historicalResponce.data.get(position).getIdUser(),
                                historicalResponce.data.get(position).getPrice(),
                                historicalResponce.data.get(position).getStatus(),
                                historicalResponce.data.get(position).getDate_created())
                        ));
                    }
                } else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String message = jsonObject.getString("message");
                        orderData.setValue(new AppResource.Error<>(message));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<AppResource<List<OrderDTO>>> call, Throwable t) {
                orderData.setValue(new AppResource.Error<>(t.getMessage()));
            }
        });
    }
}