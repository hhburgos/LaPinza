package com.cninfotech.template.buyer.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cninfotech.template.R;

import java.util.List;

public class BuyerBrandAdapter extends RecyclerView.Adapter<BuyerBrandAdapter.MyViewHolder> {


    private List<String> brandList;
    Context context;
    static boolean selected = false;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView image;
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tvBrandName);
            image = (ImageView) view.findViewById(R.id.tvBrandSelected);
        }
    }

    public BuyerBrandAdapter(Context context, List<String> brandList) {
        this.context = context;
        this.brandList = brandList;
    }

    @NonNull
    @Override
    public BuyerBrandAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_buyer_brand, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final BuyerBrandAdapter.MyViewHolder holder, int position) {
        holder.title.setText(brandList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selected){
                    holder.image.setVisibility(View.INVISIBLE);
                    selected = false;
                }else{
                    holder.image.setVisibility(View.VISIBLE);
                    selected = true;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return brandList.size();
    }
}

