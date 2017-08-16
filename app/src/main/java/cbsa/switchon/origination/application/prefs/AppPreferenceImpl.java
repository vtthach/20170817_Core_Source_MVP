package cbsa.switchon.origination.application.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import cbsa.switchon.origination.application.helper.GsonHelper;

public class AppPreferenceImpl implements AppPreference {

    private static final String PREFS_NAME = "MyApplication";
    private static final String KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN";

    private final SharedPreferences mSharedPreferences;
    private final GsonHelper gsonHelper;

    @Inject
    public AppPreferenceImpl(Context context, GsonHelper gsonHelper) {
        Context applicationContext = context.getApplicationContext();
        this.gsonHelper = gsonHelper;
        this.mSharedPreferences = applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public String getAccessToken() {
        return mSharedPreferences.getString(KEY_ACCESS_TOKEN, null);
    }
}
