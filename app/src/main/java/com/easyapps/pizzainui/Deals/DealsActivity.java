package com.easyapps.pizzainui.Deals;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.easyapps.pizzainui.MyApplication;
import com.easyapps.pizzainui.OrderDetail;
import com.easyapps.pizzainui.placeOrder.PlaceOrderActivity;
import com.easyapps.pizzainui.R;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.Orientation;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;


public class DealsActivity extends AppCompatActivity implements DiscreteScrollView.OnItemChangedListener,
        View.OnClickListener {

    private final String TAG = "flow";
    private ArrayList<DealsItem> data;
    private TextView detail;
    private TextView price;
    private DiscreteScrollView itemPicker;
    private InfiniteScrollAdapter infiniteAdapter;
    private int currentPosition = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deals_activity);
        data = new ArrayList<>();

        detail = findViewById(R.id.item_name);
        price = findViewById(R.id.item_price);

        data.add(new DealsItem("2 Zinger + 2 regular drinks", "100", R.drawable.image_one, "2 Zinger and 2 drinks"));
        data.add(new DealsItem("1 chrunch burger + 1 regular drink", "110", R.drawable.image_two, "2 Zinger and 2 drinks"));
        data.add(new DealsItem("3 Chicken piece+ 1 regular drink", "120", R.drawable.image_three, "2 Zinger and 2 drinks"));
        data.add(new DealsItem("1 shawrma + 1 regular drink", "130", R.drawable.image_one, "2 Zinger and 2 drinks"));
        data.add(new DealsItem("2 Chicken piece+ 1 regular drink", "140", R.drawable.image_two, "2 Zinger and 2 drinks"));
        data.add(new DealsItem("2 Chicken piece+ 1 regular drink", "150", R.drawable.image_one, "2 Zinger and 2 drinks"));
        data.add(new DealsItem("1 chrunch burger + 1 regular drink", "160", R.drawable.image_three, "2 Zinger and 2 drinks"));

        itemPicker = findViewById(R.id.item_picker);
        itemPicker.setOrientation(Orientation.HORIZONTAL);
        itemPicker.addOnItemChangedListener(this);
        infiniteAdapter = InfiniteScrollAdapter.wrap(new DealsAdapter(data));
        itemPicker.setAdapter(infiniteAdapter);
        itemPicker.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());

        onItemChanged(data.get(0), 0);

        findViewById(R.id.add_to_cart).setOnClickListener(this);
        findViewById(R.id.home).setOnClickListener(this);
        findViewById(R.id.leftButton).setOnClickListener(this);
        findViewById(R.id.rightButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home:
                finish();
                break;
            case R.id.leftButton:
                finish();
                break;
            case R.id.rightButton:
                start(PlaceOrderActivity.class);
                break;
            case R.id.add_to_cart:
                addToCart();
                break;
            default:
                break;
        }
    }

    private void onItemChanged(DealsItem dealsItem, int position) {
        detail.setText(dealsItem.getItemName());
        Log.i(TAG, "onItemChanged: " + dealsItem.getItemName());
        price.setText(dealsItem.getPrice() + " Rs");
        currentPosition = position;
    }

    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int position) {
        int positionInDataSet = infiniteAdapter.getRealPosition(position);
        onItemChanged(data.get(positionInDataSet), positionInDataSet);
        Log.i(TAG, "onCurrentItemChanged: Real position: " + positionInDataSet);
    }

    //-----------------------------------------------
    private void start(Class<? extends Activity> token) {
        Intent intent = new Intent(this, token);
        startActivity(intent);
    }

    //-------------------------------
    private void addToCart() {
        int positionInDataSet = infiniteAdapter.getRealPosition(itemPicker.getCurrentItem());
        DealsItem item = data.get(positionInDataSet);
        int itemPrice = Integer.parseInt(item.getPrice());
        String itemName = item.getItemName();
        OrderDetail orderDetail = new OrderDetail(itemPrice, itemName);

        Snackbar snackbar = Snackbar.make(findViewById(R.id.dealsActivity),
                itemName + "  added to order lists", Snackbar.LENGTH_SHORT);
        snackbar.show();

        MyApplication application = (MyApplication) getApplication();
        application.orderDetails.add(orderDetail);

    }// add to Cart

}
