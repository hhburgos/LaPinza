package com.cninfotech.template.buyer.dashboard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cninfotech.template.R;
import com.cninfotech.template.buyer.cart.ui.BuyerCartActivity;
import com.cninfotech.template.model.Product;

import java.util.List;

public class BuyerWishlistAdapter extends RecyclerView.Adapter<BuyerWishlistAdapter.MyViewHolder> {


    private final List<Product> productList;
    Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, price, size, color, bag, discount, discountPercent;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.tvTitle);
            price = view.findViewById(R.id.tvPrice);
            size = view.findViewById(R.id.tvSize);
            color = view.findViewById(R.id.tvColor);
            bag = view.findViewById(R.id.tvBag);
            image = view.findViewById(R.id.ivImage);
            discount = view.findViewById(R.id.tvDiscountedPrice);
            discountPercent = view.findViewById(R.id.tvDiscountedPercent);
        }
    }

    public BuyerWishlistAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public BuyerWishlistAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_buyer_wishlist, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyerWishlistAdapter.MyViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.title.setText(product.getTitle());
        holder.price.setText(product.getPrice());
        holder.size.setText(product.getSize());
        holder.color.setText(product.getColor());
        holder.image.setImageResource(product.getImage());

        if (product.getDiscount()==null){
            holder.discountPercent.setVisibility(View.GONE);
            holder.discount.setVisibility(View.GONE);
        }else{
            if (product.getDiscount().isEmpty()){
                holder.discountPercent.setVisibility(View.GONE);
                holder.discount.setVisibility(View.GONE);
            }else{
                holder.discountPercent.setVisibility(View.VISIBLE);
                holder.discount.setVisibility(View.VISIBLE);
                holder.discount.setText(product.getDiscount());
                holder.discountPercent.setText(product.getDiscountPercent());
            }
        }

        holder.bag.setOnClickListener(view -> context.startActivity(new Intent(context, BuyerCartActivity.class)));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}

