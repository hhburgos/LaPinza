package com.cninfotech.template.buyer.dashboard.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cninfotech.template.R;
import com.cninfotech.template.buyer.cart.ui.BuyerCartActivity;
import com.cninfotech.template.buyer.product.BuyerCategoryAdapter;
import com.cninfotech.template.model.Category;

import java.util.ArrayList;
import java.util.List;

public class BuyerShopFragment extends Fragment {
    private BuyerCategoryAdapter categoryAdapter;
    private List<Category> categoryList = new ArrayList<>();

    public BuyerShopFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buyer_shop, container, false);

        Window window = requireActivity().getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        androidx.appcompat.widget.Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("sape");
        ((BuyerDashboardActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        RecyclerView rvCategory = (RecyclerView) view.findViewById(R.id.rvExplore);

        categoryAdapter = new BuyerCategoryAdapter(getActivity(), categoryList);
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvCategory.setLayoutManager(new GridLayoutManager(getContext(),2));
        rvCategory.setItemAnimator(new DefaultItemAnimator());
        rvCategory.setAdapter(categoryAdapter);
        makeExplore();
        return view;
    }

    private void makeExplore() {
        Category category = new Category("Organización", "Define tu identidad", R.drawable.placeholder_large);
        categoryList.add(category);
        category = new Category("Torneo", "Organiza y optimiza", R.drawable.placeholder_large);
        categoryList.add(category);
        category = new Category("Liga", "Tabla de puntos", R.drawable.placeholder_large);
        categoryList.add(category);
        category = new Category("Formato", "Personaliza las rondas", R.drawable.placeholder_large);
        categoryList.add(category);
        /*category = new Category("Jacket", "Upto 40% OFF", R.drawable.placeholder_large);
        categoryList.add(category);
        category = new Category("Hoodie", "Upto 20% OFF", R.drawable.placeholder_large);
        categoryList.add(category);
        category = new Category("Jockey", "Upto 10% OFF", R.drawable.placeholder_large);
        categoryList.add(category);*/
        categoryAdapter.notifyDataSetChanged();
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_buyer_category, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_cart:
                startActivity(new Intent(getActivity(), BuyerCartActivity.class));
                return false;

            default:
                break;
        }

        return false;
    }
}
