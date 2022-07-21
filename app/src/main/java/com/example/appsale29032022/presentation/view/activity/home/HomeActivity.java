package com.example.appsale29032022.presentation.view.activity.home;

<<<<<<< HEAD
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.appsale29032022.R;
import com.example.appsale29032022.data.model.Food;
import com.example.appsale29032022.data.model.Order;
import com.example.appsale29032022.data.remote.dto.AppResource;
import com.example.appsale29032022.presentation.adapter.FoodAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    HomeViewModel viewModel;
    RecyclerView rcvFood;
    LinearLayout layoutLoading;
    FoodAdapter foodAdapter;
    TextView tvCountCart;
    Toolbar toolBar;
    Order order;
=======
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appsale29032022.R;
import com.example.appsale29032022.presentation.adapter.FoodAdapter;

public class HomeActivity extends AppCompatActivity {

    HomeViewModel homeViewModel;
    RecyclerView rcvFood;
    LinearLayout layoutLoading;
    FoodAdapter foodAdapter;
    Toolbar toolBar;
>>>>>>> master
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addControls();
        observerData();
        events();
    }

    private void observerData() {
<<<<<<< HEAD
        viewModel.getFoods().observe(this, new Observer<AppResource<List<Food>>>() {
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
                        Toast.makeText(HomeActivity.this, foodAppResource.message, Toast.LENGTH_SHORT).show();
                        layoutLoading.setVisibility(View.GONE);
                        break;
                }
            }
        });
        viewModel.getOrder().observe(this, new Observer<AppResource<Order>>() {
            @Override
            public void onChanged(AppResource<Order> orderAppResource) {
                switch (orderAppResource.status) {
                    case LOADING:
                        layoutLoading.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        layoutLoading.setVisibility(View.GONE);
                        order = orderAppResource.data;
                        int quantities = getQuantity(order == null ? null : order.getFoods());
                        setupBadge(quantities);
                        break;
                    case ERROR:
                        Toast.makeText(HomeActivity.this, orderAppResource.message, Toast.LENGTH_SHORT).show();
                        layoutLoading.setVisibility(View.GONE);
                        break;
                }
            }
        });
        viewModel.fetchFoods();
        foodAdapter.setOnItemClickFood(new FoodAdapter.OnItemClickFood() {
            @Override
            public void onClick(int position) {
                viewModel.fetchOrder(foodAdapter.getListFoods().get(position).getId());
=======
        homeViewModel.getFoods().observe(this, foodAppResource -> {
            switch (foodAppResource.status) {
                case LOADING:
                    layoutLoading.setVisibility(View.VISIBLE);
                    break;
                case SUCCESS:
                    layoutLoading.setVisibility(View.GONE);
                    foodAdapter.updateListProduct(foodAppResource.data);
                    break;
                case ERROR:
                    Toast.makeText(HomeActivity.this, foodAppResource.message, Toast.LENGTH_SHORT).show();
                    layoutLoading.setVisibility(View.GONE);
                    break;
>>>>>>> master
            }
        });
    }
    private void events() {
<<<<<<< HEAD
        viewModel.fetchFoods();
=======
        homeViewModel.fetchFoods();
>>>>>>> master
    }

    private void addControls() {
        layoutLoading = findViewById(R.id.layout_loading);
<<<<<<< HEAD

        toolBar = findViewById(R.id.toolbar_home);
        toolBar.setTitle("Food");
        toolBar.setTitleTextColor(getResources().getColor(R.color.primary));

        viewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new HomeViewModel();
            }
        }).get(HomeViewModel.class);
        foodAdapter = new FoodAdapter();

        // Setup RecyclerView
        rcvFood = findViewById(R.id.recycler_view_food);
        rcvFood.setAdapter(foodAdapter);
        rcvFood.setHasFixedSize(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        final MenuItem menuItem = menu.findItem(R.id.action_cart);
        View actionView = menuItem.getActionView();
        tvCountCart = actionView.findViewById(R.id.text_cart_badge);

        int quantities = getQuantity(order == null ? null : order.getFoods());
        setupBadge(quantities);

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_cart:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private int getQuantity(List<Food> listFoods) {
        if (listFoods == null) {
            return 0;
        }
        int totalQuantities = 0;
        for (Food food: listFoods) {
            totalQuantities += food.getQuantity();
        }
        return totalQuantities;
    }

    private void setupBadge(int quantities) {
        if (quantities == 0) {
            tvCountCart.setVisibility(View.GONE);
        } else {
            tvCountCart.setVisibility(View.VISIBLE);
            tvCountCart.setText(String.valueOf(Math.min(quantities, 99)));
            Log.d("TAG", "setupBadge: " + tvCountCart.getText());
        }
=======

        toolBar = findViewById(R.id.toolbar_home);
        toolBar.setTitle("Food");
        toolBar.setTitleTextColor(getResources().getColor(R.color.primary));

        homeViewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new HomeViewModel();
            }
        }).get(HomeViewModel.class);
        foodAdapter = new FoodAdapter();

        // Setup RecyclerView
        rcvFood = findViewById(R.id.recycler_view_food);
        rcvFood.setAdapter(foodAdapter);
        rcvFood.setHasFixedSize(true);
>>>>>>> master
    }
}
