package com.cninfotech.template.admin.product.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cninfotech.template.R;
import com.cninfotech.template.admin.product.InventoryAdapter;
import com.cninfotech.template.model.AdminProduct;

import java.util.ArrayList;

public class AdminInventoryFragment extends Fragment {
    RecyclerView rvInventory;
    ArrayList<AdminProduct> adminProductArrayList = new ArrayList<>();
    InventoryAdapter inventoryAdapter;

    public AdminInventoryFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_inventory, container, false);
//Recycler View for Inventory
        rvInventory = (RecyclerView) view.findViewById(R.id.rvInventory);

        inventoryAdapter = new InventoryAdapter(getActivity(), adminProductArrayList);
        rvInventory.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvInventory.setItemAnimator(new DefaultItemAnimator());
        rvInventory.setAdapter(inventoryAdapter);

        makeActivity();
        return view;
    }

    private void makeActivity() {
        AdminProduct product = new AdminProduct("Summer Wear Top", "$80.00", "86 Items left",R.drawable.placeholder_large);
        adminProductArrayList.add(product);
        product = new AdminProduct("Black Cotton Top", "$10.70", "70 Items left",R.drawable.placeholder_large);
        adminProductArrayList.add(product);
        product = new AdminProduct("Summer Full Sleeve T-Shirt", "$40.00", "38 Items left",R.drawable.placeholder_large);
        adminProductArrayList.add(product);
        product = new AdminProduct("Demin Jeans", "$50.00", "3 Items left",R.drawable.placeholder_large);
        adminProductArrayList.add(product);
        product = new AdminProduct("Baggy Pants", "$40.00", "41 Items left",R.drawable.placeholder_large);
        adminProductArrayList.add(product);
        product = new AdminProduct("Windcheater", "$70.00", "50 Items left",R.drawable.placeholder_large);
        adminProductArrayList.add(product);
        product = new AdminProduct("Nike Shorts", "$90.00", "74 Items left",R.drawable.placeholder_large);
        adminProductArrayList.add(product);
        product = new AdminProduct("Summer Wear Top", "$30.00", "3 Items left",R.drawable.placeholder_large);
        adminProductArrayList.add(product);
        inventoryAdapter.notifyDataSetChanged();
    }
}

