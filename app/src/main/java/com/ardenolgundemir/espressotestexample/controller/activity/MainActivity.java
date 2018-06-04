package com.ardenolgundemir.espressotestexample.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ardenolgundemir.espressotestexample.NewActivity;
import com.ardenolgundemir.espressotestexample.R;
import com.ardenolgundemir.espressotestexample.api.model.UserModel;
import com.ardenolgundemir.espressotestexample.controller.BaseActivity;


import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by ardenolgundemir on 1.06.2018.
 */

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button)findViewById(R.id.btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.tvTitle)).setVisibility(View.GONE);
                startActivity(new Intent(MainActivity.this,NewActivity.class));
            }
        });

        getService().getUser("ardnolgndmr")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<UserModel>() {
            @Override
            public void call(UserModel userModel) {
                    if (userModel != null){

                    }
            }
        }, throwable -> {});
    }
}


