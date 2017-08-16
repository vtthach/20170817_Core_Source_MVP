package cbsa.switchon.origination.application;

import android.app.Application;

import com.amb.retrofitwrapper.module.OkHttpModule;

import cbsa.switchon.origination.BuildConfig;
import cbsa.switchon.origination.application.injection.AppComponent;
import cbsa.switchon.origination.application.injection.DaggerAppComponent;
import cbsa.switchon.origination.application.injection.modules.AppModule;
import timber.log.Timber;


public class MyApplication extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeApp();
        initLog();
//        initCrashlytics();
    }

//    private void initCrashlytics() {
//        CrashlyticsCore core = new CrashlyticsCore.Builder()
//                .disabled(BuildConfig.DEBUG)
//                .build();
//        Fabric.with(this, new Crashlytics.Builder().core(core).build());
//    }

    private void initLog() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
//        Timber.plant(new CrashlyticsTree());
    }

    private void initializeApp() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .okHttpModule(new OkHttpModule(ApiConstants.TIMEOUT_IN_MINUTE))
                .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

//    public class CrashlyticsTree extends Timber.Tree {
//        private static final String CRASHLYTICS_KEY_PRIORITY = "priority";
//        private static final String CRASHLYTICS_KEY_TAG = "tag";
//        private static final String CRASHLYTICS_KEY_MESSAGE = "message";
//
//        @Override
//        protected void log(int priority, @Nullable String tag, @Nullable String message, @Nullable Throwable t) {
//            if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
//                return;
//            }
//
//            Crashlytics.setInt(CRASHLYTICS_KEY_PRIORITY, priority);
//            Crashlytics.setString(CRASHLYTICS_KEY_TAG, tag);
//            Crashlytics.setString(CRASHLYTICS_KEY_MESSAGE, message);
//
//            if (t == null) {
//                Crashlytics.logException(new Exception(message));
//            } else {
//                Crashlytics.logException(t);
//            }
//        }
//    }
}
