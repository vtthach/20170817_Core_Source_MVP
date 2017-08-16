package cbsa.switchon.origination.application.injection.modules;

import android.content.Context;

import com.cbsa.ui.widget.notification.version2.NotificationManager;
import com.cbsa.ui.widget.notification.version2.NotificationManagerImpl;
import com.google.gson.Gson;

import javax.inject.Singleton;

import cbsa.switchon.origination.application.helper.GsonHelper;
import cbsa.switchon.origination.application.properties.AppProperties;
import cbsa.switchon.origination.application.properties.AppPropertyHelper;
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

    @Provides
    @Singleton
    NotificationManager provideNotificationManager(Context context) {
        return new NotificationManagerImpl(context);
    }
}
