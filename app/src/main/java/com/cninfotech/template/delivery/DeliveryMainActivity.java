package com.cninfotech.template.delivery;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.cninfotech.template.R;
import com.cninfotech.template.delivery.dashboard.DeliveryAboutUsFragment;
import com.cninfotech.template.delivery.dashboard.DeliveryHomeFragment;
import com.cninfotech.template.delivery.dashboard.DeliverySupportFragment;
import com.cninfotech.template.delivery.dashboard.DeliveryTermsAndCondiitonFragment;
import com.cninfotech.template.delivery.pick.DeliveryPickFragment;
import com.cninfotech.template.delivery.wallet.DeliveryWalletFragment;
import com.cninfotech.template.startscreen.WelcomeActivity;
import com.google.android.material.navigation.NavigationView;

public class DeliveryMainActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private Toolbar toolbar;

    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // tags used to attach the fragments
    private static final String TAG_HOME = "Home";
    private static final String TAG_DELIVERY = "Delivery";
    private static final String TAG_TERMS_AND_CONDITION = "Terms and Condition";
    private static final String TAG_SUPPORT = "Support";
    private static final String TAG_ABOUT_US = "About Us";
    private static final String TAG_WALLET = "Wallet";
    private static final String TAG_LOG_OUT = "Logout";
    public static String CURRENT_TAG = TAG_HOME;


    private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_main);

        // initializing navigation menu
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mHandler = new Handler();

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }
    }

    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();
            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        Runnable mPendingRunnable = () -> {
            // update the main content by replacing fragments
            Fragment fragment = getHomeFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                    android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
            fragmentTransaction.commitAllowingStateLoss();
        };

        // If mPendingRunnable is not null, then add to the message queue
        mHandler.post(mPendingRunnable);


        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                // dashboard fragment
                setToolbarName(TAG_HOME);
                DeliveryHomeFragment deliveryHomeFragment = new DeliveryHomeFragment();
                return deliveryHomeFragment;
            case 1:
                // account fragment
                setToolbarName(TAG_DELIVERY);
                return new DeliveryPickFragment();
            case 2:
                // order fragment
                setToolbarName(TAG_TERMS_AND_CONDITION);
                return new DeliveryTermsAndCondiitonFragment();
            case 3:
                setToolbarName(TAG_SUPPORT);
                // notifications fragment
                return new DeliverySupportFragment();

            case 4:
                // chat fragment
                setToolbarName(TAG_ABOUT_US);
                return new DeliveryAboutUsFragment();
            case 5:
                // wallet fragment
                setToolbarName(TAG_WALLET);
                return new DeliveryWalletFragment();
            case 6:
                // logout fragment
                startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
                finishAffinity();
                setToolbarName(TAG_HOME);
                deliveryHomeFragment = new DeliveryHomeFragment();
                return deliveryHomeFragment;
            default:
                return new DeliveryHomeFragment();
        }
    }

    public void setToolbarName(String name) {
        toolbar.setTitle(name);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    @SuppressLint("NonConstantResourceId")
    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        // This method will trigger on item Click of navigation menu
        navigationView.setNavigationItemSelectedListener(menuItem -> {

            //Check to see which item was being clicked and perform appropriate action
            switch (menuItem.getItemId()) {
                //Replacing the main content with ContentFragment Which is our Inbox View;
                case R.id.nav_home:
                    navItemIndex = 0;
                    CURRENT_TAG = TAG_HOME;
                    break;
                case R.id.nav_delivery:
                    navItemIndex = 1;
                    CURRENT_TAG = TAG_DELIVERY;
                    break;
                case R.id.nav_terms_and_condition:
                    navItemIndex = 2;
                    CURRENT_TAG = TAG_TERMS_AND_CONDITION;
                    break;
                case R.id.nav_support:
                    navItemIndex = 3;
                    CURRENT_TAG = TAG_SUPPORT;
                    break;
                case R.id.nav_about_us:
                    navItemIndex = 4;
                    CURRENT_TAG = TAG_ABOUT_US;
                    break;
                case R.id.nav_wallet:
                    navItemIndex = 5;
                    CURRENT_TAG = TAG_WALLET;
                    break;
                case R.id.nav_log_out:
                    navItemIndex = 6;
                    CURRENT_TAG = TAG_LOG_OUT;
                    break;
                default:
                    navItemIndex = 0;
            }

            //Checking if the item is in checked state or not, if not make it in checked state
            menuItem.setChecked(!menuItem.isChecked());
            menuItem.setChecked(true);

            loadHomeFragment();

            return true;
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };
//Setting the actionbarToggle to drawer layout
        drawer.addDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        // flag to load home fragment when user presses back key
        // checking if user is on other navigation menu
        // rather than home
        if (navItemIndex != 0) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
            return;
        }

        super.onBackPressed();
    }
}
