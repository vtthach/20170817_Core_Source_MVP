package com.igap.common.widget.edittext;

import android.text.InputFilter;
import android.text.Spanned;

import timber.log.Timber;


public class RegexCharacterInputFilter implements InputFilter {

    private final String regex;

    public RegexCharacterInputFilter(String regex) {
        this.regex = regex;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        String sourceStr = source.toString();
        Timber.i("filter: " + sourceStr);
        return modifyInput(sourceStr);
    }

    private String modifyInput(String sourceStr) {
        return sourceStr.replaceAll(regex, "");
    }
}
