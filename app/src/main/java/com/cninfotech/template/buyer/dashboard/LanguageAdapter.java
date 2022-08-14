package com.cninfotech.template.buyer.dashboard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cninfotech.template.CountryModel;
import com.cninfotech.template.R;
import com.cninfotech.template.buyer.dashboard.ui.BuyerProfileFragment;

import java.util.List;

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.MyViewHolder> {


    private final List<CountryModel> countryModels;
    Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.text_language);
            image = view.findViewById(R.id.image_check);
        }
    }

    public LanguageAdapter(Context context, List<CountryModel> countryModels) {
        this.context = context;
        this.countryModels = countryModels;
    }

    @NonNull
    @Override
    public LanguageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_language_flag, parent, false);

        return new MyViewHolder(itemView);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull final LanguageAdapter.MyViewHolder holder, int position) {
        holder.title.setText(countryModels.get(position).toString());

        if (BuyerProfileFragment.defaultLanguage.contentEquals(countryModels.get(position).getCountryName())) {
            holder.image.setVisibility(View.VISIBLE);
        } else {
            holder.image.setVisibility(View.INVISIBLE);
        }

        holder.itemView.setOnClickListener(view -> {
            BuyerProfileFragment.defaultLanguage = countryModels.get(position).getCountryName();
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return countryModels.size();
    }
}

