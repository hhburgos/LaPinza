package com.cninfotech.template.admin.product;

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
import com.cninfotech.template.admin.product.ui.AdminInventoryEditActivity;
import com.cninfotech.template.model.AdminProduct;

import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.MyViewHolder> {


    private List<AdminProduct> productList;
    Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, price, orderCount;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.tvProductName);
            price = view.findViewById(R.id.tvProductPrice);
            orderCount = view.findViewById(R.id.tvProductCount);
            image = view.findViewById(R.id.ivProductImg);
        }
    }

    public InventoryAdapter(Context context, List<AdminProduct> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public InventoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_admin_inventory_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final InventoryAdapter.MyViewHolder holder, int position) {
        AdminProduct product = productList.get(position);
        holder.name.setText(product.getProductName());
        holder.price.setText(product.getProductPrice());
        holder.orderCount.setText(product.getProductTotalCount());
        holder.image.setImageResource(product.getImage());

        holder.itemView.setOnClickListener(view -> context.startActivity(new Intent(context, AdminInventoryEditActivity.class)));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}

