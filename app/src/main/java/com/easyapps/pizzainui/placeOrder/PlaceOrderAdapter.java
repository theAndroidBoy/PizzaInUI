package com.easyapps.pizzainui.placeOrder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.easyapps.pizzainui.R;

import java.util.List;

public class PlaceOrderAdapter extends RecyclerView.Adapter<PlaceOrderAdapter.PersonViewHolder> {

    private CustomItemClickListener listener;
    private List<PlaceOrderItem> PlaceOrderItems;

    PlaceOrderAdapter(List<PlaceOrderItem> PlaceOrderItems, CustomItemClickListener listener) {
        this.PlaceOrderItems = PlaceOrderItems;
        this.listener = listener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.placeorder_item,
                viewGroup, false);
        final PersonViewHolder personViewHolder = new PersonViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, personViewHolder.getAdapterPosition());
            }
        });
        return personViewHolder;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.itemName.setText(PlaceOrderItems.get(i).itemName);
        personViewHolder.itemPrice.setText("Rs "+PlaceOrderItems.get(i).price+"");
    }

    @Override
    public int getItemCount() {
        return PlaceOrderItems.size();
    }

    static class PersonViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView itemName;
        TextView itemPrice;

        PersonViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cv);
            itemName = itemView.findViewById(R.id.item_name_placeholderItem);
            itemPrice = itemView.findViewById(R.id.item_price_placeholderItem);

        }
    }
}
