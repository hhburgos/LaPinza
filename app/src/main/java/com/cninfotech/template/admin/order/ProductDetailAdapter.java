package com.cninfotech.template.admin.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cninfotech.template.R;
import com.cninfotech.template.model.AdminProduct;

import java.util.List;

public class ProductDetailAdapter extends RecyclerView.Adapter<ProductDetailAdapter.MyViewHolder> {


    private List<AdminProduct> productList;
    Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView count, price, productName;

        public MyViewHolder(View view) {
            super(view);
            count = view.findViewById(R.id.tvProductCount);
            price = view.findViewById(R.id.tvProductPrice);
            productName = view.findViewById(R.id.tvProductName);
        }
    }

    public ProductDetailAdapter(Context context, List<AdminProduct> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductDetailAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_admin_product_detail, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductDetailAdapter.MyViewHolder holder, int position) {
        AdminProduct product = productList.get(position);
        holder.count.setText(product.getProductTotalCount());
        holder.price.setText(product.getProductPrice());
        holder.productName.setText(product.getProductName());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}

