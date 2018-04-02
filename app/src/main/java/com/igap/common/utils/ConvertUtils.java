package com.igap.common.utils;


import android.support.annotation.NonNull;

public class ConvertUtils {
    public static double getDouble(@NonNull String string) {
        try {
            return Double.valueOf(string);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
