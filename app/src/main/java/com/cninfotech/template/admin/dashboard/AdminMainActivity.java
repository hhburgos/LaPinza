package com.cninfotech.template.admin.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.cninfotech.template.R;
import com.cninfotech.template.admin.chat.ui.AdminChatFragment;
import com.cninfotech.template.admin.dashboard.ui.AdminDashbaordFragment;
import com.cninfotech.template.admin.dashboard.ui.AdminFeedbackFragment;
import com.cninfotech.template.admin.order.ui.AdminOrderDashboardFragment;
import com.cninfotech.template.admin.product.ui.AdminInventoryFragment;
import com.cninfotech.template.admin.profile.AdminAccountFragment;
import com.cninfotech.template.admin.wallet.ui.AdminWalletFragment;
import com.cninfotech.template.startscreen.WelcomeActivity;
import com.google.android.material.navigation.NavigationView;

public class AdminMainActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private Toolbar toolbar;

    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // tags used to attach the fragments
    private static final String TAG_PROFILE = "Profile";
    private static final String TAG_DASHBOARD = "Dashboard";
    private static final String TAG_ORDER = "Order";
    private static final String TAG_INVENTORY = "Inventory";
    private static final String TAG_CHAT_MESSAGE = "Chat/message";
    private static final String TAG_FEEDBACK = "Feedback";
    private static final String TAG_WALLET = "Wallet";
    private static final String TAG_LOG_OUT = "Logout";
    public static String CURRENT_TAG = TAG_DASHBOARD;


    private Handler mHandler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        // initializing navigation menu
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mHandler = new Handler();

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_DASHBOARD;
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
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
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
                setToolbarName(TAG_DASHBOARD);
                AdminDashbaordFragment adminDashbaordFragment = new AdminDashbaordFragment();
                return adminDashbaordFragment;
            case 1:
                // account fragment
                setToolbarName(TAG_PROFILE);
                return new AdminAccountFragment();
            case 2:
                // order fragment
                setToolbarName(TAG_ORDER);
                return new AdminOrderDashboardFragment();
            case 3:
                setToolbarName(TAG_INVENTORY);
                // notifications fragment
                return new AdminInventoryFragment();

            case 4:
                // chat fragment
                setToolbarName(TAG_CHAT_MESSAGE);
                return new AdminChatFragment();
            case 5:
                // feddback fragment
                setToolbarName(TAG_FEEDBACK);
                return new AdminFeedbackFragment();
            case 6:
                // wallet fragment
                setToolbarName(TAG_WALLET);
                return new AdminWalletFragment();
            case 7:
                // logout fragment
                startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
                finishAffinity();
                setToolbarName(TAG_DASHBOARD);
                adminDashbaordFragment = new AdminDashbaordFragment();
                return adminDashbaordFragment;
            default:
                return new AdminDashbaordFragment();
        }
    }

    public void setToolbarName(String name){
        toolbar.setTitle(name);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_dashboard:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_DASHBOARD;
                        break;
                    case R.id.nav_account:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_PROFILE;
                        break;
                    case R.id.nav_order:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_ORDER;
                        break;
                    case R.id.nav_inventory:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_INVENTORY;
                        break;
                    case R.id.nav_chat:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_CHAT_MESSAGE;
                        break;
                    case R.id.nav_feedback:
                        navItemIndex = 5;
                        CURRENT_TAG = TAG_FEEDBACK;
                        break;
                    case R.id.nav_wallet:
                        navItemIndex = 6;
                        CURRENT_TAG = TAG_WALLET;
                        break;
                    case R.id.nav_log_out:
                        navItemIndex = 7;
                        CURRENT_TAG = TAG_LOG_OUT;
                        break;
                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
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
            CURRENT_TAG = TAG_DASHBOARD;
            loadHomeFragment();
            return;
        }

        super.onBackPressed();
    }
}
