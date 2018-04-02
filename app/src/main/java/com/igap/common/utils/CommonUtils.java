package com.igap.common.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.hardware.Camera;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

import com.igap.BuildConfig;
import com.igap.application.properties.AppProperties;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.HttpException;
import timber.log.Timber;

import static android.app.ApplicationErrorReport.TYPE_NONE;

public class CommonUtils {
    public final static DecimalFormat DECIMAL_FORMAT_DEFAULT = new DecimalFormat("#.00");
    public final static DecimalFormat DECIMAL_PERCENT_FORMAT = new DecimalFormat("#.##");

    public static boolean isHighResolution(Camera.Size sz) {
        return (sz.width >= 720 && sz.height >= 720);
    }

    public static String sha256(String base) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(base.getBytes("UTF-8"));
        StringBuffer hexString = new StringBuffer();

        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    }

    public static String trimPhoneNumber(String phoneNumber) {
        phoneNumber = phoneNumber.trim();
        phoneNumber = phoneNumber.replace(" ", "");
        return phoneNumber;
    }

    public static String getCurrentDateTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(c.getTime());
    }

    public static boolean isListValid(List<?> list) {
        return list != null && !list.isEmpty();
    }

    public static boolean isMainThread(Looper looper) {
        return looper == Looper.getMainLooper();
    }

    public static InputStream getInputStreamFromAssets(String filename, Context context)
            throws IOException {
        AssetManager manager = context.getAssets();
        return manager != null ? manager.open(filename) : null;
    }

    public static String formatMoney(double money) {
        DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols(Locale.getDefault());
        unusualSymbols.setDecimalSeparator('.');
        unusualSymbols.setGroupingSeparator(',');
        DecimalFormat formatter = new DecimalFormat("###,##0.00", unusualSymbols);
        return "$" + formatter.format(money);
    }

    public static String formatDecimal(DecimalFormat format, double value) {
        return format.format(value);
    }

    public static String formatDecimal(DecimalFormat format, float value) {
        return format.format(value);
    }

    public static boolean isNetworkError(Object e) {
        return e instanceof HttpException || e instanceof IOException;
    }

    public static int getZoomFromRatio(List<Integer> zooms, float ratio) {
        ratio *= 100;
        int index = zooms.size() - 1;
        for (int indexZoom = 0; indexZoom < zooms.size(); indexZoom++) {
            //            Timber.d("Zoom: " + indexZoom + " : " + zooms.get(indexZoom));
            if ((int) ratio == zooms.get(indexZoom)) {
                index = indexZoom;
                break;
            } else if (ratio < zooms.get(indexZoom)) {
                index = (indexZoom - 1) >= 0 ? indexZoom - 1 : 0;
                break;
            }
        }
        Timber.e("Zoom: return: " + index + " : " + ratio);
        return index;
    }

    @SuppressLint("HardwareIds")
    public static String getImeiId(Context context) {
        TelephonyManager telephonyMgr =
                (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        return telephonyMgr != null ? telephonyMgr.getDeviceId() : "";
    }

    public static String getAppVersion() {
        return BuildConfig.VERSION_NAME;
    }

    public static int getPropertyWithDefaultValue(AppProperties appProperties, String key, int defValue) {
        int rs = defValue;
        String value = appProperties.getProperty(key, defValue + "");
        try {
            rs = Integer.valueOf(value);
        } catch (NumberFormatException ignore) {
            // Ignore
        }
        Timber.i(String.format("getPropertyWithDefaultValue - %s - %s", key, value));
        return rs;
    }

    public static float getPropertyWithDefaultValue(AppProperties appProperties, String key, float defValue) {
        float rs = defValue;
        String value = appProperties.getProperty(key, defValue + "");
        try {
            rs = Float.valueOf(value);
        } catch (NumberFormatException e) {
            // Ignore
        }
        Timber.i(String.format("getPropertyWithDefaultValue - %s - %s", key, value));
        return rs;
    }

    public static boolean getPropertyWithDefaultValue(AppProperties appProperties, String key, boolean defValue) {
        boolean rs = defValue;
        String value = appProperties.getProperty(key, defValue + "");
        try {
            rs = Boolean.valueOf(value);
        } catch (NumberFormatException ignore) {
            // Ignore
        }
        Timber.i(String.format("getPropertyWithDefaultValue - %s - %s", key, value));
        return rs;
    }

    public static long getPropertyWithDefaultValue(AppProperties appProperties, String key, long defValue) {
        long rs = defValue;
        String value = appProperties.getProperty(key, defValue + "");
        try {
            rs = Long.valueOf(value);
        } catch (NumberFormatException ignore) {
            // Ignore
        }
        Timber.i(String.format("getPropertyWithDefaultValue - %s - %s", key, value));
        return rs;
    }

    public static boolean checkAppConcurrentRunning(Context context, String processPackage) {
        String basePackage = "com.amb.counter2counter";
        String currentPackage = context.getPackageName();
        if (processPackage != null && processPackage.contains(basePackage) && !processPackage.equalsIgnoreCase(currentPackage)) {
            return true;
        }
        return false;
    }

    public static boolean isSupportAutoFocus(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.camera.autofocus");
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public static boolean isWifiConnected(Context context) {
        if (isNetworkAvailable(context)) {
            ConnectivityManager cm
                    = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return ((cm != null ? cm.getActiveNetworkInfo().getType() : TYPE_NONE) == ConnectivityManager.TYPE_WIFI);
        }
        return false;
    }

    public static boolean isEthernetConnected(Context context) {
        if (isNetworkAvailable(context)) {
            ConnectivityManager cm
                    = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return ((cm != null ? cm.getActiveNetworkInfo().getType() : TYPE_NONE) == ConnectivityManager.TYPE_ETHERNET);
        }
        return false;
    }
}
