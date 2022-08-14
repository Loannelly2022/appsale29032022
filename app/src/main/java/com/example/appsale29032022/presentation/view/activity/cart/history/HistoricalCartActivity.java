package com.example.appsale29032022.presentation.view.activity.cart.history;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appsale29032022.R;
import com.example.appsale29032022.common.StringCommon;
import com.example.appsale29032022.data.model.Order;
import com.example.appsale29032022.data.remote.dto.AppResource;
import com.example.appsale29032022.presentation.adapter.OrderHistoryAdapter;

public class HistoricalCartActivity extends AppCompatActivity {
    HistoricalCartViewModel historicalCartViewModel;
    RecyclerView rcvCart;
    LinearLayout layoutLoading;
    TextView tvSumPrice, tvDate;
    int positionOrder;
    OrderHistoryAdapter orderHistoryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_historical);
        addControls();
        observerData();
        getData();
        events();
    }
    private void getData() {
        Intent intent = this.getIntent();
        positionOrder = intent.getIntExtra("position",-1);
        Log.d("TAG", "getData: " + positionOrder);
    }
    private void observerData() {
        historicalCartViewModel.getOrder().observe(this, (Observer<AppResource<Order>>) orderHistoryAppResource -> {
            switch (orderHistoryAppResource.status) {
                case LOADING:
                    layoutLoading.setVisibility(View.VISIBLE);
                    break;
                case SUCCESS:
                    layoutLoading.setVisibility(View.GONE);
                    orderHistoryAdapter.updateListProduct(orderHistoryAppResource.data.getFoods());
                    tvDate.setText(StringCommon.formatDate(orderHistoryAppResource.data.getDate_created()));
                    tvSumPrice.setText("Sum Price: "+String.format(
                            "%s VND", StringCommon.formatCurrency(orderHistoryAppResource.data.getPrice())));
                    break;
                case ERROR:
                    Toast.makeText(HistoricalCartActivity.this, orderHistoryAppResource.message, Toast.LENGTH_SHORT).show();
                    layoutLoading.setVisibility(View.GONE);
                    break;
            }
        });
    }
    private void events() {
        historicalCartViewModel.fetchCartOrderHistory();
    }
    private void addControls() {
        layoutLoading = findViewById(R.id.layout_loading);
        tvDate = findViewById(R.id.textview_date_history);
        tvSumPrice = findViewById(R.id.textview_sum_price_history);
        historicalCartViewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new HistoricalCartViewModel(HistoricalCartActivity.this,positionOrder);
            }
        }).get(HistoricalCartViewModel.class);
        orderHistoryAdapter = new OrderHistoryAdapter();

        // Setup RecyclerView
        rcvCart = findViewById(R.id.recycler_view_cart_history);
        rcvCart.setAdapter(orderHistoryAdapter);
        rcvCart.setHasFixedSize(true);

    }
}