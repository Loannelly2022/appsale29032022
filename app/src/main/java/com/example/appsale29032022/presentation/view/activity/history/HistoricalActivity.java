package com.example.appsale29032022.presentation.view.activity.history;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appsale29032022.R;
import com.example.appsale29032022.data.model.Order;
import com.example.appsale29032022.data.remote.dto.AppResource;
import com.example.appsale29032022.presentation.adapter.HistoricalAdapter;
import com.example.appsale29032022.presentation.view.activity.cart.history.HistoricalCartActivity;

import java.util.ArrayList;
import java.util.List;

public class HistoricalActivity extends AppCompatActivity {

    HistoricalViewModel historicalViewModel;
    RecyclerView rcvHistory;
    LinearLayout layoutLoading;
    TextView tvSumPrice;
    HistoricalAdapter historicalAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical);
        addControls();
        observerData();
        events();
    }
    private void observerData() {
        historicalViewModel.getHistory().observe(this, (Observer<AppResource<List<Order>>>) historicalAppResource -> {
            switch (historicalAppResource.status) {
                case LOADING:
                    layoutLoading.setVisibility(View.VISIBLE);
                    break;
                case SUCCESS:
                    layoutLoading.setVisibility(View.GONE);
                    if(historicalAppResource.data.size()<=0){
                        layoutLoading.setVisibility(View.GONE);
                        List<Order> orderList = new ArrayList<>();
                        orderList.add(null);
                        historicalAdapter.updateListOrder(orderList);
                    }else{
                        historicalAdapter.updateListOrder(historicalAppResource.data);
                    }
                    historicalAdapter.setOnItemClickOrderHitory(position -> openItemCartHistory(position));
                    break;
                case ERROR:
                    Toast.makeText(HistoricalActivity.this, historicalAppResource.message, Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }
    private void events() {
        historicalViewModel.fetchHistory();
    }

    private void openItemCartHistory(int position) {
        Intent intent = new Intent(this, HistoricalCartActivity.class);
        intent.putExtra("position",position);
        startActivity(intent);
    }
    private void addControls() {
        layoutLoading = findViewById(R.id.layout_loading);
        tvSumPrice = findViewById(R.id.textview_sum_price);
        historicalViewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new HistoricalViewModel(HistoricalActivity.this);
            }
        }).get(HistoricalViewModel.class);
        historicalAdapter = new HistoricalAdapter();

        // Setup RecyclerView
        rcvHistory = findViewById(R.id.recycler_view_history);
        rcvHistory.setAdapter(historicalAdapter);
        rcvHistory.setHasFixedSize(true);
    }
}