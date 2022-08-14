package com.cninfotech.template.buyer.dashboard.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.cninfotech.template.R;
import com.cninfotech.template.buyer.dashboard.BuyerCategoryHomeAdapter;
import com.cninfotech.template.buyer.dashboard.BuyerFeatureImageAdapter;
import com.cninfotech.template.buyer.dashboard.BuyerPopularProductAdapter;
import com.cninfotech.template.buyer.product.BuyerProductAdapter;
import com.cninfotech.template.buyer.dashboard.BuyerTopTrendingAdapter;
import com.cninfotech.template.model.Category;
import com.cninfotech.template.model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class BuyerHomeFragment extends Fragment {

    private final List<Product> productList = new ArrayList<>();

    private BuyerProductAdapter productAdapter;

    private final ArrayList<Integer> ImagesArray = new ArrayList<>();
    private static final Integer[] IMAGES = {R.drawable.placeholder_wide,
            R.drawable.placeholder_wide,
            R.drawable.placeholder_wide,
            R.drawable.placeholder_wide,
            R.drawable.placeholder_wide,
            R.drawable.placeholder_wide,
            R.drawable.placeholder_wide};

    LinearLayout sliderDotsPanel;
    private int dotsCount;
    private ImageView[] dots;

    CardView cardShop;

    ArrayList<Category> categories = new ArrayList<>();

    public BuyerHomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buyer_home, container, false);

        Window window = requireActivity().getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        bannerImage(view);
        designCollection(view);
        popularItem(view);
        topTending(view);
        category(view);

        topTrending();
        categories();

        return view;
    }

    Timer timer;
    Handler handler;

    private void bannerImage(View view) {
        ViewPager vpFeatureImage = view.findViewById(R.id.vpFeaturedImage);
        sliderDotsPanel = view.findViewById(R.id.SliderDots);

        ImagesArray.addAll(Arrays.asList(IMAGES));
        vpFeatureImage.setAdapter(new BuyerFeatureImageAdapter(getContext(), ImagesArray));

        dotsCount = IMAGES.length;
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {

            dots[i] = new ImageView(getContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_unslected));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotsPanel.addView(dots[i], params);

        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_selected));

        vpFeatureImage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotsCount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_unslected));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_selected));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        cardShop = view.findViewById(R.id.card_shop);
        cardShop.setOnClickListener(view12 -> ((BuyerDashboardActivity) requireActivity()).changeFragment(new BuyerShopFragment(), BuyerShopFragment.class.getSimpleName()));


        handler = new Handler();
        timer = new Timer();
        final Runnable runnable = () -> {
            int currentPage = vpFeatureImage.getCurrentItem();
            //return to first page, if current page is last page
            if (currentPage == IMAGES.length - 1) {
                currentPage = -1;
            }
            vpFeatureImage.setCurrentItem(++currentPage, true);
        };
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 5000, 10000);
    }

    private void designCollection(View view) {
        RecyclerView rvDesignerCollection = view.findViewById(R.id.rvDesignerCollection);
        rvDesignerCollection.setFocusable(false);

        productAdapter = new BuyerProductAdapter(getActivity(), productList);
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvDesignerCollection.setLayoutManager(mLayoutManager);
        rvDesignerCollection.setItemAnimator(new DefaultItemAnimator());
        rvDesignerCollection.setAdapter(productAdapter);
    }

    private void topTending(View view) {
        RecyclerView rvTopTrending = view.findViewById(R.id.rvTopTrends);

        BuyerTopTrendingAdapter topTrendingAdapter = new BuyerTopTrendingAdapter(getActivity(), productList);
        RecyclerView.LayoutManager mLayoutManager2 =
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvTopTrending.setLayoutManager(mLayoutManager2);
        rvTopTrending.setItemAnimator(new DefaultItemAnimator());
        rvTopTrending.setAdapter(topTrendingAdapter);
    }

    private void popularItem(View view) {
        RecyclerView rvPopularProduct = view.findViewById(R.id.rvPopularItems);

        BuyerPopularProductAdapter buyerPopularProductAdapter = new BuyerPopularProductAdapter(getActivity(), productList);
        rvPopularProduct.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvPopularProduct.setItemAnimator(new DefaultItemAnimator());
        rvPopularProduct.setAdapter(buyerPopularProductAdapter);
    }

    private void category(View view) {
        RecyclerView rvCategory = (RecyclerView) view.findViewById(R.id.rvCategories);

        BuyerCategoryHomeAdapter categoryAdapter = new BuyerCategoryHomeAdapter(getActivity(), categories);
        rvCategory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvCategory.setItemAnimator(new DefaultItemAnimator());
        rvCategory.setAdapter(categoryAdapter);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void topTrending() {
        Product product = new Product("Both Side Winter Fleece Zipper Jacket For Women's By Rc", R.drawable.placeholder_large, "$34.99", "#808080", "$30.99", "-10%", 3);
        productList.add(product);
        product = new Product("Aamayra Fashion House Cream Woolen Kurti For Women", R.drawable.placeholder_large, "$14.99", "#808080", "", "", 4.5f);
        productList.add(product);
        product = new Product("Nyptra Black Grey Premium Oversize Swed Fur Bomber Jacket For Women", R.drawable.placeholder_large, "$24.99", "#808080", "$20.99", "-10%", 3.5f);
        productList.add(product);
        productAdapter.notifyDataSetChanged();
    }

    private void categories() {
        Category category = new Category(
                "Torneo",
                "",
                R.drawable.ic_t_shirt
        );

        categories.add(category);

        category = new Category(
                "Organizacion",
                "",
                R.drawable.ic_shoppingbag
        );
        categories.add(category);

        category = new Category(
                "Jacket",
                "",
                R.drawable.ic_jacket
        );
        categories.add(category);

        category = new Category(
                "Pant",
                "",
                R.drawable.ic_pant
        );
        categories.add(category);

        category = new Category(
                "Shoes",
                "",
                R.drawable.ic_shoes
        );
        categories.add(category);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (timer != null) {
            timer.cancel();
        }
    }
}
