package com.igap.application.injection.modules;

import com.google.gson.Gson;
import com.igap.application.helper.GsonHelper;
import com.igap.application.properties.AppProperties;
import com.igap.application.properties.AppPropertyHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class HelperModule {

    @Provides
    @Singleton
    GsonHelper provideGsonHelper(Gson gson) {
        return new GsonHelper(gson);
    }

    @Provides
    @Singleton
    AppPropertyHelper provideAppPropertyHelper(AppProperties appproperties) {
        return new AppPropertyHelper(appproperties);
    }
}
