package com.ardenolgundemir.espressotestexample;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.ardenolgundemir.espressotestexample.controller.activity.MainActivity;
import com.ardenolgundemir.espressotestexample.controller.activity.TakeAPhotoActivity;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        onOpenApp();

    }

    void onOpenApp(){
        Handler handler = new Handler();
        handler.postDelayed(() -> {
             startActivity(new Intent(SplashActivity.this, TakeAPhotoActivity.class));
            finish();
        }, 500);
    }
}
