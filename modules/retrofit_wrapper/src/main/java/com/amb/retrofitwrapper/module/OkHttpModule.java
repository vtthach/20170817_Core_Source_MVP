package com.amb.retrofitwrapper.module;

import com.amb.retrofitwrapper.RetrofitConstants;
import com.amb.retrofitwrapper.ssl.RetrofitClientBuilder;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class OkHttpModule {

    private int timeoutInMinute;

    public OkHttpModule(int timeoutInMinute) {
        this.timeoutInMinute = timeoutInMinute;
    }

    @Provides
    @Singleton
    @Named(RetrofitConstants.OK_HTTP_IGNORE_CERTIFICATE)
    OkHttpClient provideOkHttpClient(@Named(RetrofitConstants.ENABLE_LOG) boolean isEnableLog) {
        RetrofitClientBuilder retrofitClientBuilder = new RetrofitClientBuilder()
                .ignoreCertificates(timeoutInMinute);
        if (isEnableLog) {
            retrofitClientBuilder = retrofitClientBuilder.log(HttpLoggingInterceptor.Level.BODY);
        }
        return retrofitClientBuilder.build();
    }
}
