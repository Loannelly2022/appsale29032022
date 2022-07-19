package com.example.appsale29032022.presentation.view.activity.home;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appsale29032022.R;
import com.example.appsale29032022.data.model.Food;
import com.example.appsale29032022.data.model.User;
import com.example.appsale29032022.data.remote.dto.AppResource;
import com.example.appsale29032022.presentation.adapter.FoodAdapter;
import com.example.appsale29032022.presentation.view.activity.sign_in.SignInActivity;
import com.example.appsale29032022.presentation.view.activity.sign_up.SignUpActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    HomeViewModel viewModel;
    LinearLayout layoutLoading;
    RecyclerView rcvFood;
    FoodAdapter foodAdapter;
    TextView tvCountCart;
    List<Food> listCart;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        addControls(view);
        observerData();
        return view;
    }

    private void observerData() {
        viewModel.getFoods().observe(getActivity(), new Observer<AppResource<List<Food>>>() {
            @Override
            public void onChanged(AppResource<List<Food>> foodAppResource) {
                switch (foodAppResource.status) {
                    case LOADING:
                        layoutLoading.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        layoutLoading.setVisibility(View.GONE);
                        foodAdapter.updateListProduct(foodAppResource.data);
                        break;
                    case ERROR:
                        Toast.makeText(getActivity(), foodAppResource.message, Toast.LENGTH_SHORT).show();
                        layoutLoading.setVisibility(View.GONE);
                        break;
                }
            }
        });
        viewModel.fetchFoods();
    }

    private void addControls(View view) {
        rcvFood = view.findViewById(R.id.recyclerView);
        layoutLoading = view.findViewById(R.id.layout_loading);
        listCart = new ArrayList<>();
        viewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new HomeViewModel();
            }
        }).get(HomeViewModel.class);
        foodAdapter = new FoodAdapter();
        rcvFood.setAdapter(foodAdapter);
        rcvFood.setHasFixedSize(true);
    }
}