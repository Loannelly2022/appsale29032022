package com.example.appsale29032022.presentation.view.activity.home;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appsale29032022.data.model.Food;
import com.example.appsale29032022.data.remote.dto.AppResource;
import com.example.appsale29032022.data.repository.AuthenticationRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {
    private final AuthenticationRepository authenticationRepository;
    private MutableLiveData<AppResource<List<Food>>> resourceFood;


    public HomeViewModel() {
        authenticationRepository = new AuthenticationRepository();
        if (resourceFood == null) {
            resourceFood = new MutableLiveData<>();
        }
    }

    public LiveData<AppResource<List<Food>>> getFoods() {
        return resourceFood;
    }


    public void fetchFoods() {
        resourceFood.setValue(new AppResource.Loading(null));
        Call<AppResource<List<Food>>> callFoods = authenticationRepository.fetchFoods();
        callFoods.enqueue(new Callback<AppResource<List<Food>>>() {
            @Override
            public void onResponse(Call<AppResource<List<Food>>> call, Response<AppResource<List<Food>>> response) {
                if (response.isSuccessful()) {
                    AppResource<List<Food>> foodResponse = response.body();
                    if (foodResponse.data != null) {
                        resourceFood.setValue(new AppResource.Success<>(foodResponse.data));
                    }
                } else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String message = jsonObject.getString("message");
                        resourceFood.setValue(new AppResource.Error<>(message));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<AppResource<List<Food>>> call, Throwable t) {
                resourceFood.setValue(new AppResource.Error<>(t.getMessage()));
            }
        });
    }

}
