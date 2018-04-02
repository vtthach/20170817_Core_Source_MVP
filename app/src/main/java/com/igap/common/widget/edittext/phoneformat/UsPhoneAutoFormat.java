package com.igap.common.widget.edittext.phoneformat;


import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;

import java.util.Locale;

public class UsPhoneAutoFormat extends PhoneNumberFormattingTextWatcher {

    public UsPhoneAutoFormat() {
        super(Locale.US.getCountry());
    }

    @Override
    public synchronized void afterTextChanged(Editable s) {
        String str = s.toString();
        if (str.length() < 11) {
            super.afterTextChanged(s);
        }
    }
}