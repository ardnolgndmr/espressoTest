package com.ardenolgundemir.espressotestexample.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ardenolgundemir.espressotestexample.NewActivity;
import com.ardenolgundemir.espressotestexample.R;
import com.ardenolgundemir.espressotestexample.api.WebServices;
import com.ardenolgundemir.espressotestexample.api.model.UserModel;
import com.ardenolgundemir.espressotestexample.controller.BaseActivity;


import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by ardenolgundemir on 1.06.2018.
 */

public class MainActivity extends BaseActivity {

    CountingIdlingResource espressoTestIdlingResource = new CountingIdlingResource("Network_Call");
    String username = "";
    TextView tvUserName;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvUserName = (TextView)findViewById(R.id.tvUserName);


        ((Button)findViewById(R.id.btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this,NewActivity.class));
                espressoTestIdlingResource.increment();
                getService().getUser("ardnolgndmr")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<UserModel>() {
                    @Override
                    public void call(UserModel userModel) {
                        if (userModel != null){
                            username = userModel.getLogin();
                            tvUserName.setText(userModel.getLogin());
                        }
                        espressoTestIdlingResource.decrement();

                    }
                }, throwable -> {   espressoTestIdlingResource.decrement();});
            }
        });

    }
    public String getUsername(){
         return username;
    }
    public CountingIdlingResource getEspressoIdlingResourceForMainActivity() {
        return espressoTestIdlingResource;
    }
}


