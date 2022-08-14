package com.cninfotech.template.buyer.product;

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
import com.cninfotech.template.model.Product;
import com.cninfotech.template.buyer.product.ui.BuyerParticularCategoryActivity;
import com.cninfotech.template.buyer.product.ui.BuyerProductActvity;

import java.util.List;

public class BuyerProductAdapter extends RecyclerView.Adapter<BuyerProductAdapter.MyViewHolder> {


    private List<Product> productList;
    Context context;
    static boolean fav = false;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, price, discount, discountPercent;
        public ImageView image, favorite;
        public RatingBar rating;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.tvTitle);
            price = view.findViewById(R.id.tvPrice);
            image = view.findViewById(R.id.ivImage);
            favorite = view.findViewById(R.id.ivFavorite);
            discount = view.findViewById(R.id.tvDiscountedPrice);
            discountPercent = view.findViewById(R.id.tvDiscountedPercent);
            rating = view.findViewById(R.id.rating_bar);
        }
    }

    public BuyerProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public BuyerProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = null;
        if (context instanceof BuyerParticularCategoryActivity) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_buyer_particular_category, parent, false);
        } else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_buyer_designer_collection, parent, false);
        }
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final BuyerProductAdapter.MyViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.title.setText(product.getTitle());
        holder.price.setText(product.getPrice());
        holder.image.setImageResource(product.getImage());
        holder.rating.setRating(product.getRating());

        holder.itemView.setOnClickListener(view -> context.startActivity(new Intent(context, BuyerProductActvity.class)));
        holder.favorite.setOnClickListener(view -> {
            if (fav) {
                holder.favorite.setImageResource(R.drawable.ic_favorite);
                fav = false;
            } else {
                holder.favorite.setImageResource(R.drawable.ic_baseline_favorite_24);
                fav = true;
            }
        });

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
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}

