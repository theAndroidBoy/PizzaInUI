package com.easyapps.pizzainui.placeOrder;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.easyapps.pizzainui.MyApplication;
import com.easyapps.pizzainui.R;

import java.util.ArrayList;
import java.util.List;

public class PlaceOrderActivity extends Activity {

    private List<PlaceOrderItem> placeOrderItems;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.placeorder_activity);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
    }

    private void initializeData() {
        placeOrderItems = new ArrayList<>();
        MyApplication application = (MyApplication) getApplication();

        for (int i = 0; i < application.orderDetails.size(); i++) {
            placeOrderItems.add(new PlaceOrderItem(application.orderDetails.get(i).getItemName(),
                    application.orderDetails.get(i).getPrice()));
        }

    }

    private void initializeAdapter() {
        PlaceOrderAdapter adapter = new PlaceOrderAdapter(placeOrderItems, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(PlaceOrderActivity.this,
                        "its working ,Item Clicked: " + position, Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
