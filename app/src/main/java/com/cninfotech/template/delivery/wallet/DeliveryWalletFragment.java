package com.cninfotech.template.delivery.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cninfotech.template.R;
import com.cninfotech.template.admin.wallet.RecentActivityAdapter;
import com.cninfotech.template.admin.wallet.ui.AdminSendtoBankActivity;
import com.cninfotech.template.model.AdminTransaction;

import java.util.ArrayList;

public class DeliveryWalletFragment extends Fragment {
    RecyclerView rvWallet;
    ArrayList<AdminTransaction> adminTransactions = new ArrayList<>();
    RecentActivityAdapter recentActivityAdapter;

    TextView btnSend;
    public DeliveryWalletFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delivery_wallet, container, false);
        btnSend = view.findViewById(R.id.btnSendToBank);
        //Recycler View for Order
        rvWallet = (RecyclerView) view.findViewById(R.id.rvAdminRecentActivity);

        recentActivityAdapter = new RecentActivityAdapter(adminTransactions);
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvWallet.setLayoutManager(mLayoutManager);
        rvWallet.setItemAnimator(new DefaultItemAnimator());
        rvWallet.setAdapter(recentActivityAdapter);

        makeActivity();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AdminSendtoBankActivity.class));
            }
        });
        return view;
    }
    private void makeActivity() {
        AdminTransaction adminTransaction = new AdminTransaction("Order num 24718","$55.7",
                "$10.01","3 items |30 June 2020","Order","Earnings");
        adminTransactions.add(adminTransaction);
        adminTransaction = new AdminTransaction("Order num 24515","$77.7",
                "$2.01","15 items |4 August 2020","Cash on delivery","Earnings");
        adminTransactions.add(adminTransaction);
        adminTransaction = new AdminTransaction("Send to Bank","",
                "-$201","30 June 2020","","Send to Bank");
        adminTransactions.add(adminTransaction);
        adminTransaction = new AdminTransaction("Order num 24515","$17.7",
                "$2.01","3 items |30 June 2020","Order","Earnings");
        adminTransactions.add(adminTransaction);
        adminTransaction = new AdminTransaction("Order num 24515","$97.7",
                "$2.01","3 items |30 June 2020","PayPal","Earnings");
        adminTransactions.add(adminTransaction);
        adminTransaction = new AdminTransaction("Send to Bank","",
                "-$101","30 June 2020","","Send to Bank");
        adminTransactions.add(adminTransaction);
        recentActivityAdapter.notifyDataSetChanged();
    }
}
