package com.cninfotech.template.buyer.dashboard.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cninfotech.template.R;
import com.cninfotech.template.buyer.dashboard.BuyerWishlistAdapter;
import com.cninfotech.template.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BuyerWishListFragment extends Fragment {
    private BuyerWishlistAdapter wishlistAdapter;
    private List<Product> productList = new ArrayList<>();

    public BuyerWishListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buyer_wishlist, container, false);

        RecyclerView rvWishlist = (RecyclerView) view.findViewById(R.id.rvWishlist);

        wishlistAdapter = new BuyerWishlistAdapter(getActivity(),productList);
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvWishlist.setLayoutManager(mLayoutManager);
        rvWishlist.setItemAnimator(new DefaultItemAnimator());
        rvWishlist.setAdapter(wishlistAdapter);
        makeWishList();
        return view;
    }

    private void makeWishList() {
        Product product = new Product("Both Side Winter Fleece Zipper Jacket For Women's By Rc",R.drawable.placeholder_small,"$4.99","M","Grey","$3.99","-10%");
        productList.add(product);
        product = new Product("Aamayra Fashion House Cream Woolen Kurti For Women",R.drawable.placeholder_small,"$5.99","L","White","$4.99","-10%");
        productList.add(product);
        product = new Product("Nyptra Black Grey Premium Oversize Swed Fur Bomber Jacket For Women",R.drawable.placeholder_small,"$6.99","S","Black","$2.99","-10%");
        productList.add(product);
        product = new Product("Both Side Winter Fleece Zipper Jacket For Women's By Rc",R.drawable.placeholder_small,"$8.99","S","Purple","$1.99","-10%");
        productList.add(product);
        product = new Product("Nyptra Black Grey Premium Oversize Swed Fur Bomber Jacket For Women",R.drawable.placeholder_small,"$16.99","S","Black","$14.99","-10%");
        productList.add(product);
        product = new Product("Both Side Winter Fleece Zipper Jacket For Women's By Rc",R.drawable.placeholder_small,"$16.99","XL","Black","$12.99","-10%");
        productList.add(product);
        product = new Product("Aamayra Fashion House Cream Woolen Kurti For Women",R.drawable.placeholder_small,"$16.99","S","Black","$14.99","-10%");
        productList.add(product);
        wishlistAdapter.notifyDataSetChanged();
    }
}
