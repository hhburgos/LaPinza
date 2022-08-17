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
import com.cninfotech.template.model.Usuario;

import java.util.List;

public class MemberAdapter extends ArrayAdapter<Usuario> {
    private Context context;
    private List<Usuario> dataSet;

    public MemberAdapter(@NonNull Context context, List<Usuario> objects) {
        super(context, R.layout.row_member, objects);

        this.context = context;
        this.dataSet = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(this.context).inflate(R.layout.row_member, parent, false);

        TextView tvEmail = convertView.findViewById(R.id.textView);
        ImageView ivRemove = convertView.findViewById(R.id.removeMember);

        tvEmail.setText(this.dataSet.get(position).getEmail());
        ivRemove.setOnClickListener(v -> {
            this.dataSet.remove(position);
            MemberAdapter.this.notifyDataSetChanged();
        });

        return convertView;
    }
}
