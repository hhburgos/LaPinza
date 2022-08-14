package com.cninfotech.template.admin.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cninfotech.template.R;

import java.util.List;

public class ProductImageListAdapter extends RecyclerView.Adapter<ProductImageListAdapter.MyViewHolder> {


    private List<Integer> productList;
    Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.ivProductImg);
        }
    }

    public ProductImageListAdapter(Context context, List<Integer> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductImageListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_admin_edit_product_image_scroll, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductImageListAdapter.MyViewHolder holder, int position) {
        holder.image.setImageResource(productList.get(position));

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}

