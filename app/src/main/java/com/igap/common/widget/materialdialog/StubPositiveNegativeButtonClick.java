package com.igap.common.widget.materialdialog;

import android.support.annotation.CallSuper;

import com.afollestad.materialdialogs.MaterialDialog;

public class StubPositiveNegativeButtonClick implements IPositiveNegativeClickListener {
    private final boolean autoHideNeg;
    private final boolean autoHidePos;

    public StubPositiveNegativeButtonClick(boolean autoHidePos, boolean autoHideNeg) {
        this.autoHideNeg = autoHideNeg;
        this.autoHidePos = autoHidePos;
    }

    public StubPositiveNegativeButtonClick() {
        this.autoHideNeg = true;
        this.autoHidePos = true;
    }

    @CallSuper
    @Override
    public void onPositiveButtonClicked(MaterialDialog dialog) {
        if (autoHidePos) {
            dialog.dismiss();
        }
        onStubPositiveBtnClicked();
    }

    @CallSuper
    @Override
    public void onNegativeButtonClicked(MaterialDialog dialog) {
        if (autoHideNeg) {
            dialog.dismiss();
        }
        onStubNegativeBtnClicked();
    }

    public void onStubNegativeBtnClicked() {
        // Stub method
    }

    public void onStubPositiveBtnClicked() {
        // Stub method
    }
}
