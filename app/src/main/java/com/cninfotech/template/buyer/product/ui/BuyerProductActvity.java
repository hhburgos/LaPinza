package com.cninfotech.template.buyer.product.ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.cninfotech.template.R;
import com.cninfotech.template.buyer.cart.ui.BuyerDetailFragment;
import com.cninfotech.template.buyer.cart.ui.BuyerReviewFragment;
import com.cninfotech.template.buyer.cart.ui.BuyerShippingActivity;
import com.cninfotech.template.buyer.product.BuyerProductImagesAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BuyerProductActvity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Integer> ImagesArray = new ArrayList<>();
    private static final Integer[] IMAGES= {R.drawable.placeholder_wide,R.drawable.placeholder_wide,R.drawable.placeholder_wide};

    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;

    ImageView favorite;
    static boolean fav = false;

    FloatingActionButton fab2;
    FloatingActionButton fab1;
    FloatingActionButton fab;

    TextView label1;
    TextView label2;

    static boolean isFABOpen = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_product);
        init();

    }

    private void init() {
        //Toolbar declaration
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ViewPager vpFeatureImage = findViewById(R.id.vpFeaturedImage);
        sliderDotspanel = findViewById(R.id.SliderDots);

        favorite = findViewById(R.id.ivFavorite);
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fav){
                    favorite.setImageResource(R.drawable.ic_favorite);
                    fav = false;
                }else{
                    favorite.setImageResource(R.drawable.ic_baseline_favorite_24);
                    fav = true;
                }
            }
        });

        ImagesArray.addAll(Arrays.asList(IMAGES));
        vpFeatureImage.setAdapter(new BuyerProductImagesAdapter(getApplicationContext(),ImagesArray));

        dotscount =IMAGES.length;
        dots = new ImageView[dotscount];

        for(int i = 0; i < dotscount; i++){

            dots[i] = new ImageView(getApplicationContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_unslected));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_selected));

        vpFeatureImage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_unslected));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_selected));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        createFab();

        ViewPager viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab:{
                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
                break;
            }
            case R.id.fab1:{
                //Open cart added dialog
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                @SuppressLint("InflateParams") View view1 = Objects.requireNonNull(inflater).inflate(R.layout.alert_buyer_success_banner, null);
                final Dialog dialog = new Dialog(BuyerProductActvity.this,android.R.style.Theme_Material_Light_Dialog_Alert);

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(view1);
                dialog.setCancelable(false);
                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setCanceledOnTouchOutside(false);
                ImageView dismiss = dialog.findViewById(R.id.ivDismiss);
                Button checkOut = dialog.findViewById(R.id.btnCheckout);
                Button conShopping = dialog.findViewById(R.id.btnContinue);

                dismiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                checkOut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        startActivity(new Intent(getApplicationContext(), BuyerShippingActivity.class));
                    }
                });

                conShopping.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;
            }
            case R.id.fab2:{
                startActivity(new Intent(getApplicationContext(), BuyerSizeSelectorActivity.class));
                break;
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void createFab() {
        fab = findViewById(R.id.fab);
        fab1 = findViewById(R.id.fab1);
        fab2 = findViewById(R.id.fab2);
        label1 = findViewById(R.id.tvLabelFab1);
        label2 = findViewById(R.id.tvLabelFab2);

        fab.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab1.setOnClickListener(this);
    }

    private void showFABMenu(){
        isFABOpen=true;
        label1.setVisibility(View.VISIBLE);
        label1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        label2.setVisibility(View.VISIBLE);
        label2.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
        fab1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        fab2.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
    }

    private void closeFABMenu(){
        isFABOpen=false;
        label1.animate().translationY(0);
        label1.setVisibility(View.GONE);
        label2.setVisibility(View.GONE);
        label2.animate().translationY(0);
        fab1.animate().translationY(0);
        fab2.animate().translationY(0);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new BuyerDetailFragment(), "Detail");
        adapter.addFragment(new BuyerReviewFragment(), "Review");
        viewPager.setAdapter(adapter);
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
