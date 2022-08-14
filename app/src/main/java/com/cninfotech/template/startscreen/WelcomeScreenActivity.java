package com.cninfotech.template.startscreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.cninfotech.template.R;
import com.cninfotech.template.buyer.dashboard.BuyerSlidingImageAdapter;
import com.skyfishjy.library.RippleBackground;

import java.util.ArrayList;

public class WelcomeScreenActivity extends AppCompatActivity implements View.OnClickListener {

    private static final Integer[] IMAGES = {R.drawable.placeholder_large, R.drawable.placeholder_large, R.drawable.placeholder_large};
    private static final String[] HEADING = {"Shop your favorite", "Explore new designs", "Buy the latest"};
    private static final String[] SLOGAN = {"Personalise your favorite brand by following the top brands", "Personalise your favorite brand by following the top brands", "Personalise your favorite brand by following the top brands"};

    private final ArrayList<Integer> ImagesArray = new ArrayList<>();
    private final ArrayList<String> HeadingArray = new ArrayList<>();
    private final ArrayList<String> SloganArray = new ArrayList<>();

    LinearLayout sliderDotspanel;
    private int dotscount;

    private ImageView[] dots;
    CardView btnStart;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_welcome_screen);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        init();
    }

    private void init() {
        btnStart = findViewById(R.id.btnStart);
        final RippleBackground rippleBackground= findViewById(R.id.content);
        rippleBackground.startRippleAnimation();

        btnStart.setOnClickListener(this);

        for (int i = 0; i < IMAGES.length; i++) {
            ImagesArray.add(IMAGES[i]);
            HeadingArray.add(HEADING[i]);
            SloganArray.add(SLOGAN[i]);
        }

        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);

        ViewPager mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new BuyerSlidingImageAdapter(WelcomeScreenActivity.this, ImagesArray, HeadingArray, SloganArray));
        mPager.setOffscreenPageLimit(3);
        mPager.setPageMargin(30);
        mPager.setClipToPadding(false);
        mPager.setCurrentItem(1);
        mPager.setPadding(140, 0, 140, 0);
        mPager.setPageTransformer(false, (page, position) -> {
            if (position < -1) {
                page.setScaleY(0.7f);
                page.setScaleX(.7f + Math.abs(1) * (1 - .7f));
            } else if (position <= 1) {
                page.setScaleY((1 - Math.abs(position) * (1 - 0.7f)));
                page.setAlpha(1);
            } else {
                page.setScaleY(0.7f);
                page.setScaleX(.7f + Math.abs(1) * (1 - .7f));
            }

        });

        dotscount = 3;
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_unslected));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_selected));

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_unslected));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_selected));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStart:
                startActivity(new Intent(getApplicationContext(),WelcomeActivity.class));
                break;
        }
    }
}
