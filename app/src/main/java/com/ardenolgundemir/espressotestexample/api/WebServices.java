package com.ardenolgundemir.espressotestexample.api;



import com.ardenolgundemir.espressotestexample.api.model.UserModel;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by ardenolgundemir on 29/08/2017.
 */

public interface WebServices {

    @GET("users/{username}")
    Observable<UserModel> getUser(@Path("username") String username);

}