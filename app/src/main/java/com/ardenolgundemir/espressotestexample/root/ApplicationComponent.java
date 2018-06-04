package com.ardenolgundemir.espressotestexample.root;




import com.ardenolgundemir.espressotestexample.controller.BaseActivity;

import dagger.Component;

/**
 * Created by ardenolgundemir on 01/06/2017.
 */
@ScopeContext
@Component(modules ={ApiModule.class}, dependencies = App.IMAppComponent.class)
public interface ApplicationComponent {
    void inject(BaseActivity splashActivity);
}
