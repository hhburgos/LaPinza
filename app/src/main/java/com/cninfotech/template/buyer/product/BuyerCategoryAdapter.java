package com.cninfotech.template.buyer.product;

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
import com.cninfotech.template.buyer.product.ui.BuyerParticularCategoryActivity;
import com.cninfotech.template.model.Category;

import java.util.List;

public class BuyerCategoryAdapter extends RecyclerView.Adapter<BuyerCategoryAdapter.MyViewHolder> {


    private List<Category> categoryList;
    Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, slogan;
        public ImageView image;
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tvCategory);
            slogan = (TextView) view.findViewById(R.id.tvCategorySlogan);
            image = (ImageView) view.findViewById(R.id.ivImage);
        }
    }

    public BuyerCategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public BuyerCategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_buyer_explore, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyerCategoryAdapter.MyViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.title.setText(category.getCategoryName());
        holder.slogan.setText(category.getCategorySlogan());
        holder.image.setImageResource (category.getCategoryImg());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, BuyerParticularCategoryActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}

