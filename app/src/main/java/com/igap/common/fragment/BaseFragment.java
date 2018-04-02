package com.igap.common.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.igap.application.MyApplication;
import com.igap.application.prefs.AppPreference;
import com.igap.common.utils.SpannableUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment {

    private Unbinder unBinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onInitComponent();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        unBinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unBinder != null) {
            unBinder.unbind();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        hideToast();
    }

    protected void onInitComponent() {
        // Stub method
    }

    protected abstract int getLayoutId();

    protected void showToastError(String errorMessage) {
        // TODO vtt
    }

    protected void hideToast() {
        // TODO vtt
    }

    protected CharSequence getSpannableString(@StringRes int strId, int color) {
        return SpannableUtils.getSpanColor(getString(strId), color);
    }

    protected void showSnackBarMessage(@StringRes int snackBarMsg) {
        showSnackBarMessage(getString(snackBarMsg));
    }

    protected void showSnackBarMessage(String message) {
        View view = getView();
        if (view != null) {
            Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
        }
    }

    protected void onInvalidToken() {
        if (isFinishIfNotLogin()) {
            getActivity().finish();
        }
    }

    protected AppPreference getAppPreference() {
        return MyApplication.getAppComponent().appPreference();
    }

    protected boolean isFinishIfNotLogin() {
        return true;
    }
}
