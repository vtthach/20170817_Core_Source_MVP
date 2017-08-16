package cbsa.switchon.origination.application.properties;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import timber.log.Timber;

public class AppProperties {
    private final Context context;
    private final String propertyFilePath;

    Properties prop = new Properties();

    public AppProperties(Context context, String propertyFilePath) {
        this.context = context.getApplicationContext();
        this.propertyFilePath = propertyFilePath;
    }

    public void resetProperties() {
        if (context != null) {
            loadFromAssetFile(context, prop);
            loadPropertiesFromFile(context);
        }
    }

    public static void loadFromAssetFile(@NonNull Context context, Properties prop) {
        Timber.i("loadFromAssetFile called");
        try {
            InputStream is = context.getAssets().open("properties/application.properties");
            prop.load(is);
            is.close();
        } catch (IOException e) {
            Timber.e(e, "loadFromAssetFile error: " + e.getMessage());
        }
    }

    public void loadPropertiesFromFile(@NonNull Context context) {
        try {
            File file = new File(propertyFilePath);
            if (file.exists()) {
                Timber.i("loadFromFile called: file" + file.getPath());
                InputStream fileStream = new FileInputStream(file);
                prop.load(fileStream);
                fileStream.close();
            }
        } catch (IOException e) {
            Timber.e("loadFromFile failed :" + e);
        }
    }

    public String getProperty(String key, String defValue) {
        return prop.getProperty(key, defValue);
    }
}
