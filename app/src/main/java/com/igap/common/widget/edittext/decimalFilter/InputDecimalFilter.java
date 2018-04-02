package com.igap.common.widget.edittext.decimalFilter;

import android.text.InputFilter;
import android.text.Spanned;

public class InputDecimalFilter implements InputFilter {

    @Override
    public CharSequence filter(CharSequence source, int i, int i2, Spanned spanned, int i3, int i4) {
        int dotPos = -1;
        int len = spanned.length();
        for (int decimalsI = 0; decimalsI < len; decimalsI++) {
            char c = spanned.charAt(decimalsI);
            if (c == '.') {
                dotPos = decimalsI;
                break;
            }
        }
        if (dotPos >= 0) {
            // protects against many dots
            if (source.equals(".") || source.equals(",")) {
                return "";
            }
        }
        return null;
    }
}