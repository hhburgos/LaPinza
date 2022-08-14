package com.cninfotech.template.admin.order;

import android.annotation.SuppressLint;
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
import com.cninfotech.template.admin.order.ui.AdminOrderDetailActivity;
import com.cninfotech.template.model.AdminProduct;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {


    private List<AdminProduct> productList;
    Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, price, time, deliveryInfo, orderNumber, orderCount, paymentType;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.tvCustomerName);
            price = (TextView) view.findViewById(R.id.tvPrice);
            time = (TextView) view.findViewById(R.id.tvDeliveryTime);
            deliveryInfo = (TextView) view.findViewById(R.id.tvDeliveryInfo);
            image = (ImageView) view.findViewById(R.id.ivProductImg);
            orderNumber = (TextView) view.findViewById(R.id.tvOrderNumber);
            orderCount = (TextView) view.findViewById(R.id.tvProductCount);
            paymentType = (TextView) view.findViewById(R.id.tvDeliveryMethod);
        }
    }

    public ProductAdapter(Context context, List<AdminProduct> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_admin_order__products, parent, false);

        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ProductAdapter.MyViewHolder holder, int position) {
        AdminProduct product = productList.get(position);

        holder.name.setText(product.getProductName());
        holder.price.setText("Total : "+product.getProductPrice());
        holder.time.setText(product.getProductTime());
        holder.paymentType.setText(product.getProductPaymentMethod());
        holder.orderCount.setText(product.getProductTotalCount());
        holder.orderNumber.setText("Order no: "+ product.getOrderNumber());
        holder.deliveryInfo.setText(product.getProductDeliveryInfo());
        holder.image.setImageResource(product.getImage());

        holder.itemView.setOnClickListener(view -> context.startActivity(new Intent(context, AdminOrderDetailActivity.class)));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}

