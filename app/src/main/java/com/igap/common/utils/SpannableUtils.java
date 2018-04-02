package com.igap.common.utils;


import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

public class SpannableUtils {

    public static Spannable getSpanColor(String originalText, int color) {
        Spannable spannable = new SpannableString(originalText);
        ForegroundColorSpan fgcspan = new ForegroundColorSpan(color);
        spannable.setSpan(fgcspan, 0, originalText.length(), 0);
        return spannable;
    }
}
