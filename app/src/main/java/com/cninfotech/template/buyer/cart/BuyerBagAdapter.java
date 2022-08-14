package com.cninfotech.template.buyer.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cninfotech.template.R;
import com.cninfotech.template.model.Product;

import java.util.List;

public class BuyerBagAdapter extends RecyclerView.Adapter<BuyerBagAdapter.MyViewHolder> {


    private List<Product> productList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, price, size, color, increase, decrease, number;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tvTitle);
            price = (TextView) view.findViewById(R.id.tvPrice);
            size = (TextView) view.findViewById(R.id.tvSize);
            color = (TextView) view.findViewById(R.id.tvColor);
            image = (ImageView) view.findViewById(R.id.ivImage);
            increase = (TextView) view.findViewById(R.id.tvIncrease);
            decrease = (TextView) view.findViewById(R.id.tvDecrease);
            number = (TextView) view.findViewById(R.id.tvCount);
        }
    }

    public BuyerBagAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public BuyerBagAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_buyer__bag, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final BuyerBagAdapter.MyViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.title.setText(product.getTitle());
        holder.price.setText(product.getPrice());
        holder.size.setText(product.getSize());
        holder.color.setText(product.getColor());
        holder.image.setImageResource(product.getImage());

        holder.decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(holder.number.getText().toString()) > 0) {
                    holder.number.setText(String.valueOf(Integer.parseInt(holder.number.getText().toString()) - 1));
                }
            }
        });

        holder.increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(holder.number.getText().toString()) < 20) {
                    holder.number.setText(String.valueOf(Integer.parseInt(holder.number.getText().toString()) + 1));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}

