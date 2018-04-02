package com.igap.common.widget.edittext.decimalFilter;

import android.text.InputFilter;
import android.text.Spanned;

public class DecimalFilter implements InputFilter {

    private int decimalDigits;

    public DecimalFilter(int decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    @Override
    public CharSequence filter(CharSequence source, int i, int i2, Spanned spanned, int i3, int i4) {
        // Source : input text
        // span: current text

        int dotPos = -1;
        int len = spanned.length();
        for (int decimalsI = 0; decimalsI < len; decimalsI++) {
            char c = spanned.charAt(decimalsI);
            if (c == '.' || c == ',') {
                dotPos = decimalsI;
                break;
            }
        }
        if (dotPos >= 0) {

            // protects against many dots
            if (source.equals(".") || source.equals(",")) {
                return "";
            }
            // if the text is entered before the dot
            if (i4 <= dotPos) {
                return null;
            }
            if (len - dotPos > decimalDigits) {
                return "";
            }
        }

        return null;
    }
}