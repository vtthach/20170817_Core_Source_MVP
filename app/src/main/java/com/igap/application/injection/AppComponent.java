package com.igap.application.injection;

import android.content.Context;

import com.amb.retrofitwrapper.RetrofitConstants;
import com.amb.retrofitwrapper.module.OkHttpModule;
import com.amb.retrofitwrapper.module.RetrofitModule;
import com.igap.application.helper.GsonHelper;
import com.igap.application.injection.modules.AppModule;
import com.igap.application.injection.modules.HelperModule;
import com.igap.application.prefs.AppPreference;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {AppModule.class,
        OkHttpModule.class,
        RetrofitModule.class,
        HelperModule.class})
public interface AppComponent {
    @Named(RetrofitConstants.RETROFIT_IGNORE_CERTIFICATE)
    Retrofit retrofit();

    @Named(RetrofitConstants.OK_HTTP_IGNORE_CERTIFICATE)
    OkHttpClient okHttpClient();

    CallAdapter.Factory factory();

    AppPreference appPreference();

    GsonHelper gsonHelper();

    Context appContext();
}
