package com.ardenolgundemir.espressotestexample.utility;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.ardenolgundemir.espressotestexample.R;


/**
 * Created by ardenolgundemir on 26/02/2017.
 */

public class SharedPref {
    private static SharedPreferences mSharedPref;

    public static void init(Context context)
    {
        if(mSharedPref == null)
            mSharedPref = context.getSharedPreferences(context.getString(R.string.app_name), Activity.MODE_PRIVATE);
    }

    public static String read(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static void write(String key, String value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }

    public static boolean read(String key, boolean defValue) {
        return mSharedPref.getBoolean(key, defValue);
    }

    public static void write(String key, boolean value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putBoolean(key, value);
        prefsEditor.commit();
    }

    public static int read(String key, int defValue) {
        return mSharedPref.getInt(key, defValue);
    }

    public static void write(String key, int value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putInt(key, value).commit();
    }

    public static void deleteAllKey() {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.clear().commit();
    }
}