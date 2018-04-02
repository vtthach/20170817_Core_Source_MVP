package com.igap.common.utils;

import com.google.common.base.Strings;

public class ValidationUtils {
    public final static boolean isValidEmail(String target) {
        if (Strings.isNullOrEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
