package com.igap.application.injection.modules;

import android.content.Context;

import com.amb.retrofitwrapper.RetrofitConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.igap.BuildConfig;
import com.igap.application.MyApplication;
import com.igap.application.prefs.AppPreference;
import com.igap.application.prefs.AppPreferenceImpl;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module
public class AppModule {

    private final MyApplication application;

    public AppModule(MyApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    AppPreference provideAppPreference(AppPreferenceImpl appPreference) {
        return appPreference;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().serializeNulls().create();
    }

    @Provides
    @Singleton
    @Named(RetrofitConstants.ENABLE_LOG)
    boolean provideEnableLog() {
        return BuildConfig.DEBUG;
    }
}
