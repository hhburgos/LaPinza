package com.cninfotech.template.admin.wallet;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cninfotech.template.R;
import com.cninfotech.template.model.AdminTransaction;

import java.util.List;

public class RecentActivityAdapter extends RecyclerView.Adapter<RecentActivityAdapter.MyViewHolder> {


    private List<AdminTransaction> adminTransactionList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView transactionDetail, totalCash, cash, deliveryInfo, orderDetail, cashInfo;

        public MyViewHolder(View view) {
            super(view);
            transactionDetail = view.findViewById(R.id.tvTransactionName);
            totalCash = view.findViewById(R.id.tvTotalCash);
            cash = view.findViewById(R.id.tvCash);
            deliveryInfo = view.findViewById(R.id.tvCashInfo);
            orderDetail = view.findViewById(R.id.tvTransactionInfo);
            cashInfo = view.findViewById(R.id.tvCashDetail);
        }
    }

    public RecentActivityAdapter(List<AdminTransaction> adminTransactionList) {
        this.adminTransactionList = adminTransactionList;
    }

    @NonNull
    @Override
    public RecentActivityAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_admin_wallet, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecentActivityAdapter.MyViewHolder holder, int position) {
        AdminTransaction adminTransaction = adminTransactionList.get(position);
        holder.transactionDetail.setText(adminTransaction.getActivity());
        holder.totalCash.setText(adminTransaction.getTotalCash());
        if (!adminTransaction.getCashInfo().matches("Earnings")){
            holder.cash.setTextColor(Color.parseColor("#ffcc0000"));
        }
        holder.cash.setText(adminTransaction.getCash());
        holder.cashInfo.setText(adminTransaction.getCashInfo());
        holder.orderDetail.setText(adminTransaction.getItemDetail());
        holder.deliveryInfo.setText(adminTransaction.getDeliveryInfo());
    }

    @Override
    public int getItemCount() {
        return adminTransactionList.size();
    }
}

