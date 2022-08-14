package com.cninfotech.template.buyer.dashboard.ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cninfotech.template.R;
import com.cninfotech.template.buyer.product.ui.BuyerParticularCategoryActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class BuyerDashboardActivity extends AppCompatActivity {

    FrameLayout container;

    final FragmentManager fm = getSupportFragmentManager();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_dashboard);

        container = findViewById(R.id.frame_container);

        viewAdBanner();
        initFragments();
    }

    private void initFragments() {
        final BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        changeFragment(new BuyerHomeFragment(), BuyerHomeFragment.class.getSimpleName());
    }

    private void viewAdBanner() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View view = Objects.requireNonNull(inflater).inflate(R.layout.alert_buyer_ad_banner, null);

        final Dialog dialog = new Dialog(this,android.R.style.Theme_Material_Light_Dialog_Alert);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        dialog.setCancelable(true);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(true);
        ImageView dismiss = dialog.findViewById(R.id.ivDismiss);
        Button shopNow = dialog.findViewById(R.id.btnShopNow);
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        shopNow.setOnClickListener(view1 -> {
            dialog.dismiss();
            startActivity(new Intent(getApplicationContext(), BuyerParticularCategoryActivity.class));
        });
        dialog.show();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_explore:
                    changeFragment(new BuyerShopFragment(), BuyerShopFragment.class.getSimpleName());
                    return true;
                case R.id.nav_shop:
                    changeFragment(new BuyerHomeFragment(), BuyerHomeFragment.class.getSimpleName());
                    return true;
                case R.id.nav_brand:
                    changeFragment(new BuyerWishListFragment(), BuyerWishListFragment.class.getSimpleName());
                    return true;
                case R.id.nav_profile:
                    changeFragment(new BuyerProfileFragment(), BuyerProfileFragment.class.getSimpleName());
                    return true;
            }
            return false;
        }
    };

    public void changeFragment(Fragment fragment, String tagFragmentName) {

        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        Fragment currentFragment = mFragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }

        //pre-defined function on every view call
        Fragment fragmentTemp = mFragmentManager.findFragmentByTag(tagFragmentName);
        if (fragmentTemp == null) {
            fragmentTemp = fragment;
            fragmentTransaction.add(R.id.frame_container, fragmentTemp, tagFragmentName);
        } else {
            fragmentTransaction.show(fragmentTemp);
        }
        fragmentTransaction.setPrimaryNavigationFragment(fragmentTemp);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNowAllowingStateLoss();
    }
}
