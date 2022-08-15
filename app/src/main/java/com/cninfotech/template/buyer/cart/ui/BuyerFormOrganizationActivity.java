package com.cninfotech.template.buyer.cart.ui;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.cninfotech.template.R;
import com.cninfotech.template.buyer.cart.MemberAdapter;

import java.util.ArrayList;
import java.util.List;

public class BuyerFormOrganizationActivity extends AppCompatActivity {
    private RecyclerView accordionView;
    private List<String> members;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_form);

        this.accordionView = findViewById(R.id.rvAcordionMember);

        createRecyclerView();
    }

    private void createRecyclerView() {
        this.members = new ArrayList<>();
        this.members.add("");
        MemberAdapter memberAdapter = new MemberAdapter(BuyerFormOrganizationActivity.this, this.members);
        this.accordionView.setAdapter(memberAdapter);
        this.accordionView.setHasFixedSize(true);
    }
}
