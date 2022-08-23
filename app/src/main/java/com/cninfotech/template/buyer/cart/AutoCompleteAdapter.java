package com.cninfotech.template.buyer.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cninfotech.template.R;
import com.cninfotech.template.model.Organizacion;

import java.util.List;

public class AutoCompleteAdapter extends ArrayAdapter<Organizacion> {
    private Context context;
    private List<Organizacion> items;

    public AutoCompleteAdapter(@NonNull Context context, List<Organizacion> items) {
        super(context, android.R.layout.simple_list_item_1, items);
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(this.context).inflate(android.R.layout.simple_list_item_1, parent, false);

        TextView textView = convertView.findViewById(android.R.id.text1);

        textView.setText(this.items.get(position).getTitulo());

        return convertView;
    }
}
