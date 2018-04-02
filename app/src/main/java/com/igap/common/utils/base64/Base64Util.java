package com.igap.common.utils.base64;


import com.igap.common.utils.FileUtils;

import java.io.File;
import java.io.IOException;

import timber.log.Timber;

public class Base64Util {

    private Base64Util() {
        // Private
    }

    public static String getBase64FromFile(String filePath) {
        byte[] imageByteData = FileUtils.getByteFromFile(new File(filePath));
        return getBase64FromByteArray(imageByteData);
    }

    public static String getBase64FromByteArray(byte[] imageByteData) {
        if (imageByteData != null) {
            return getBase64FromByte(imageByteData);
        } else {
            // throw new RuntimeException("Get base 64 error!!! +  imageByteData is null (check again filepath)"); // Use this line if want to catch exception when file not found!!!
            return "";
        }
    }

    public static String getBase64FromByte(byte[] bytes) {
        return Base64Support.encodeToString(bytes, Base64Support.NO_WRAP);
    }

    public static byte[] getByteFromBase64(String base64) {
        return Base64Support.decode(base64, Base64Support.NO_WRAP);
    }

    public static boolean getFileFromBase64(String filePath, String base64) {
        boolean rs = true;
        try {
            FileUtils.writeFile(Base64Support.decode(base64, Base64Support.NO_WRAP), filePath);
        } catch (IllegalArgumentException e) {
            rs = false;
            Timber.e(e, "getFileFromBase64 IllegalArgumentException error: " + e.getMessage());
        } catch (IOException e) {
            rs = false;
            Timber.e(e, "getFileFromBase64 IOException error: " + e.getMessage());
        }
        return rs;
    }
}
