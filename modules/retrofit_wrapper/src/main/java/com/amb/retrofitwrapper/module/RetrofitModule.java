package com.amb.retrofitwrapper.module;


import com.amb.retrofitwrapper.RetrofitConstants;
import com.google.gson.Gson;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@Singleton
public class RetrofitModule {

    protected final String baseUrl;

    public RetrofitModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    @Named(RetrofitConstants.RETROFIT_IGNORE_CERTIFICATE)
    Retrofit provideRetrofit(Gson gson, @Named(RetrofitConstants.OK_HTTP_IGNORE_CERTIFICATE) OkHttpClient okHttpClient, CallAdapter.Factory callAdapterFactory) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .addCallAdapterFactory(callAdapterFactory)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    CallAdapter.Factory provideCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }
}
