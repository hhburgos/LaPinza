package com.cninfotech.template.admin.order.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.cninfotech.template.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class AdminOrderDashboardFragment extends Fragment {

    FrameLayout container;
    private Fragment newOrder = new AdminNewOrderFragment();
    private Fragment oldOrder = new AdminPastOrderFragment();

    FragmentManager fm  ;

    Fragment active = newOrder;

    public AdminOrderDashboardFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_order, container, false);
        Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        fm = getChildFragmentManager();
        initFragments(view);
        return view;
    }
    private void initFragments(View view) {
        final BottomNavigationView navigation = view.findViewById(R.id.navigation);
        container = view.findViewById(R.id.frame_container);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm.beginTransaction().add(R.id.frame_container, oldOrder, "2").hide(oldOrder).commit();
        fm.beginTransaction().add(container.getId(), newOrder, "1").commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_old_order:
                    fm.beginTransaction().hide(active).show(oldOrder).commit();
                    active = oldOrder;
                    return true;
                case R.id.nav_new_order:
                    fm.beginTransaction().hide(active).show(newOrder).commit();
                    active = newOrder;
                    return true;
            }
            return false;
        }
    };
}
