package com.ardenolgundemir.espressotestexample.controller;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


import com.ardenolgundemir.espressotestexample.api.WebServices;
import com.ardenolgundemir.espressotestexample.root.ApiModule;
import com.ardenolgundemir.espressotestexample.root.App;
import com.ardenolgundemir.espressotestexample.root.ApplicationComponent;
import com.ardenolgundemir.espressotestexample.root.DaggerApplicationComponent;
import com.ardenolgundemir.espressotestexample.utility.Constant;

import javax.inject.Inject;

/**
 * Created by ardenolgundemir on 01/06/2017.
 */

public class BaseActivity extends AppCompatActivity {
    @Inject
    WebServices webServices;
    private App app;
    private ApplicationComponent component;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (App) getApplication();
        component = DaggerApplicationComponent.builder()
                .iMAppComponent(app.component())
                .apiModule(new ApiModule())
                .build();
        component.inject(this);
    }


    public WebServices getService() {
        return this.webServices;
    }


    protected void Toast(String Message) {
        Toast.makeText(getApplicationContext(), Message, Toast.LENGTH_SHORT)
                .show();
    }
}
