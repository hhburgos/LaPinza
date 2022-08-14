package com.cninfotech.template.admin.order.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cninfotech.template.R;
import com.cninfotech.template.admin.order.ProductDetailAdapter;
import com.cninfotech.template.model.AdminProduct;

import java.util.ArrayList;

public class AdminOrderListFragment extends Fragment {
    RecyclerView rvOrder;
    ArrayList<AdminProduct> adminProducts = new ArrayList<>();
    ProductDetailAdapter productAdapter;

    public AdminOrderListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_product_detail, container, false);
//Recycler View for Detail
        rvOrder = (RecyclerView) view.findViewById(R.id.rvDetail);

        productAdapter = new ProductDetailAdapter(getActivity(), adminProducts);
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvOrder.setLayoutManager(mLayoutManager);
        rvOrder.setItemAnimator(new DefaultItemAnimator());
        rvOrder.setAdapter(productAdapter);
        makeOrder();
        return view;
    }

    private void makeOrder() {
        AdminProduct product = new AdminProduct("Adidas Boots", "$70.5", "3",R.drawable.placeholder_large);
        adminProducts.add(product);
        product = new AdminProduct("T-Shirt", "$50.5", "1",R.drawable.placeholder_large);
        adminProducts.add(product);
        product = new AdminProduct("Sweat Shirt", "$20.5", "4",R.drawable.placeholder_large);
        adminProducts.add(product);
        product = new AdminProduct("Demin Pant", "$40.5", "2",R.drawable.placeholder_large);
        adminProducts.add(product);
        product = new AdminProduct("Nike Hoddy", "$62.5", "3",R.drawable.placeholder_large);
        adminProducts.add(product);
        product = new AdminProduct("Caliber Shoes", "$11", "7",R.drawable.placeholder_large);
        adminProducts.add(product);
        productAdapter.notifyDataSetChanged();
    }
}
