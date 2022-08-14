package com.cninfotech.template.startscreen;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cninfotech.template.R;

public class SplashActivity extends AppCompatActivity {

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_splash);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        Thread background = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                    Intent i = new Intent(getBaseContext(), WelcomeScreenActivity.class);
                    startActivity(i);
                } catch (Exception ignored) {
                }
            }
        };
        // start thread
        background.start();
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
        finish();
    }
}
