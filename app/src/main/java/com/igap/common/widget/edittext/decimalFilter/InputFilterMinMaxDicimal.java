package com.igap.common.widget.edittext.decimalFilter;

import android.text.InputFilter;
import android.text.Spanned;

public class InputFilterMinMaxDicimal implements InputFilter {

    private double min, max;

    public InputFilterMinMaxDicimal(double min, double max) {
        this.min = Math.min(min, max);
        this.max = Math.max(min, max);
    }

    @Override
    public CharSequence filter(CharSequence source, int i, int i2, Spanned spanned, int i3, int i4) {
        try {
            double input = Double.parseDouble(spanned.toString() + source.toString());
            if (isInRange(min, max, input)) {
                return null;
            }
        } catch (NumberFormatException ignore) {
        }
        return "";
    }

    private boolean isInRange(double min, double max, double value) {
        return value >= min && value <= max;
    }
}