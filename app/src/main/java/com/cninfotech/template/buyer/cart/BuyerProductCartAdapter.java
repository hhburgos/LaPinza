package com.cninfotech.template.buyer.cart;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cninfotech.template.R;
import com.cninfotech.template.buyer.product.ui.BuyerParticularCategoryActivity;
import com.cninfotech.template.buyer.product.ui.BuyerProductActvity;
import com.cninfotech.template.model.Product;

import java.util.List;

public class BuyerProductCartAdapter extends RecyclerView.Adapter<BuyerProductCartAdapter.MyViewHolder> {


    private List<Product> productList;
    Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, price, quantity;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.text_product_name);
            price = view.findViewById(R.id.text_total_price);
            quantity = view.findViewById(R.id.text_quantity);
        }
    }

    public BuyerProductCartAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public BuyerProductCartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = null;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_buyer_product_list_name, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final BuyerProductCartAdapter.MyViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.title.setText(product.getTitle());
        holder.price.setText(product.getPrice());
        holder.quantity.setText(" x ".concat(product.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}

