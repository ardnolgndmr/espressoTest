package com.ardenolgundemir.espressotestexample.root;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;


import com.ardenolgundemir.espressotestexample.utility.SharedPref;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Created by ardenolgundemir on 9.05.2018.
 */

public class App extends Application {
    private IMAppComponent component;
    private static Context context;

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPref.init(getApplicationContext());
        component = DaggerApp_IMAppComponent.builder().
                iMAppModule(new IMAppModule(this)).
                build();

        component.inject(this);
        context = getApplicationContext();
    }

    public static App get(Activity activity) {
        if (activity == null)
            return null;
        return (App) activity.getApplication();
    }

    public static Context getContext() {
        return context;
    }

    public IMAppComponent component() {
        return this.component;
    }

    @Component(modules = IMAppModule.class)
    public interface IMAppComponent {
        void inject(App app);
    }

    @Module
    public static final class IMAppModule {
        private final App app;

        public IMAppModule(App app) {
            this.app = app;
        }

        @Singleton
        @Provides
        App provideApp() {
            return this.app;
        }
    }
}
