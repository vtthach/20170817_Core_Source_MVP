package cbsa.switchon.origination.common.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cbsa.ui.widget.notification.enumeration.NotificationType;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cbsa.switchon.origination.application.MyApplication;
import cbsa.switchon.origination.application.prefs.AppPreference;
import cbsa.switchon.origination.common.utils.SpannableUtils;


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
        MyApplication.getAppComponent().notificationManager().show(errorMessage, NotificationType.ERROR);
    }

    protected void hideToast() {
        MyApplication.getAppComponent().notificationManager().hide();
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
