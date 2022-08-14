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
import com.cninfotech.template.buyer.product.ui.BuyerProductActvity;
import com.cninfotech.template.model.Product;

import java.util.List;

public class BuyerTopTrendingAdapter extends RecyclerView.Adapter<BuyerTopTrendingAdapter.MyViewHolder> {


    private List<Product> productList;
    Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, price, discount, discountPercent;
        public ImageView image;
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tvTitle);
            price = (TextView) view.findViewById(R.id.tvPrice);
            image = (ImageView) view.findViewById(R.id.ivImage);
            discount = view.findViewById(R.id.tvDiscountedPrice);
            discountPercent = view.findViewById(R.id.tvDiscountedPercent);
        }
    }

    public BuyerTopTrendingAdapter(Context context, List<Product> productList) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public BuyerTopTrendingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_buyer_top_trends, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyerTopTrendingAdapter.MyViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.title.setText(product.getTitle());
        holder.price.setText(product.getPrice());
        holder.image.setImageResource (product.getImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, BuyerProductActvity.class));
            }
        });

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

    @Override
    public int getItemCount() {
        return productList.size();
    }
}

