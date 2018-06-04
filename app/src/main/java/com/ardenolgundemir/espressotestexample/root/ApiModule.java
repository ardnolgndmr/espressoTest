package com.ardenolgundemir.espressotestexample.root;


import com.ardenolgundemir.espressotestexample.BuildConfig;
import com.ardenolgundemir.espressotestexample.api.WebServices;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ardenolgundemir on 01/06/2017.
 */
@ScopeContext
@Module
public class ApiModule {

    @Provides
    @ScopeContext
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }
        return httpLoggingInterceptor;
    }

    //OkHttpClient
    @Provides
    @ScopeContext
    OkHttpClient.Builder provideHttpLogLevel(HttpLoggingInterceptor httpLoggingInterceptor) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS);

        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Developer", "1")
                    .header("Platform", "Android")
                    .build();
            return chain.proceed(request);
        });

        return httpClient.addInterceptor(httpLoggingInterceptor);  // <-- this is the important line!

    }

    @Provides
    @ScopeContext
    GsonConverterFactory provideGsonConverter() {
        return GsonConverterFactory.create(new Gson());

    }

    @Provides
    @ScopeContext
    Retrofit provideRestAdapter(GsonConverterFactory gsonConverter, OkHttpClient.Builder okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASEURL)
                .addConverterFactory(gsonConverter)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient.build())
                .build();
    }

    @Provides
    @ScopeContext
    WebServices provideIMService(Retrofit restAdapter) {
        return restAdapter.create(WebServices.class);
    }
}
