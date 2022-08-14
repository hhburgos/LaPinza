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
import com.cninfotech.template.admin.order.ProductAdapter;
import com.cninfotech.template.model.AdminProduct;

import java.util.ArrayList;

public class AdminPastOrderFragment extends Fragment {
    RecyclerView rvOrder;
    ArrayList<AdminProduct> adminProducts = new ArrayList<>();
    ProductAdapter productAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_admin_past_order, container, false);
        //Recycler View for WishList
        rvOrder = (RecyclerView) v.findViewById(R.id.rvAdminOrder);

        productAdapter = new ProductAdapter(getActivity(),adminProducts);
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvOrder.setLayoutManager(mLayoutManager);
        rvOrder.setItemAnimator(new DefaultItemAnimator());
        rvOrder.setAdapter(productAdapter);

        makeOrder();
        return v;
    }

    private void makeOrder() {
        AdminProduct product = new AdminProduct(201651,"Nyptra Black Grey Premium Oversize Swed Fur Bomber Jacket For Women","20 June, 10:45am","$20.5","Cash on Delivery",
                "Delivered","3 items",R.drawable.placeholder_small);
        adminProducts.add(product);
        product = new AdminProduct(206546,"Both Side Winter Fleece Zipper Jacket For Women's By Rc","20 July, 11:45am","$20.5","Cash on Delivery",
                "Pending","3 items",R.drawable.placeholder_small);
        adminProducts.add(product);
        product = new AdminProduct(105141,"Nyptra Black Grey Premium Oversize Swed Fur Bomber Jacket For Women","20 September, 12:45am","$20.5","Card Payment",
                "Delivered","3 items",R.drawable.placeholder_small);
        adminProducts.add(product);
        product = new AdminProduct(215002,"Both Side Winter Fleece Zipper Jacket For Women's By Rc","20 August, 4:45am","$20.5","Cash on Delivery",
                "Pending","3 items",R.drawable.placeholder_small);
        adminProducts.add(product);
        product = new AdminProduct(502054,"Nyptra Black Grey Premium Oversize Swed Fur Bomber Jacket For Women","20 February, 3:45am","$20.5","Card Payment",
                "Delivered","3 items",R.drawable.placeholder_small);
        adminProducts.add(product);
        productAdapter.notifyDataSetChanged();
    }
}

