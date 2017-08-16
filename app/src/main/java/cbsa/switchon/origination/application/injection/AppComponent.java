package cbsa.switchon.origination.application.injection;

import android.content.Context;

import com.amb.retrofitwrapper.RetrofitConstants;
import com.amb.retrofitwrapper.module.OkHttpModule;
import com.amb.retrofitwrapper.module.RetrofitModule;
import com.cbsa.ui.widget.notification.version2.NotificationManager;

import javax.inject.Named;
import javax.inject.Singleton;

import cbsa.switchon.origination.application.helper.GsonHelper;
import cbsa.switchon.origination.application.injection.modules.AppModule;
import cbsa.switchon.origination.application.injection.modules.HelperModule;
import cbsa.switchon.origination.application.prefs.AppPreference;
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

    NotificationManager notificationManager();

    Context appContext();
}
