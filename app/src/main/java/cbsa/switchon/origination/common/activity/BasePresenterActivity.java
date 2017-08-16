package cbsa.switchon.origination.common.activity;


import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.cbsa.ui.widget.notification.enumeration.NotificationType;

import cbsa.switchon.origination.common.presenter.BasePresenter;
import cbsa.switchon.origination.common.presenter.BasePresenterView;

public abstract class BasePresenterActivity<T extends BasePresenter> extends BaseActivity implements BasePresenterView {

    @NonNull
    protected abstract T getPresenter();

    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenter().onCreate(savedInstanceState);
    }

    @Override
    @CallSuper
    protected void onResume() {
        super.onResume();
        getPresenter().onResume();
    }

    @Override
    @CallSuper
    protected void onPause() {
        super.onPause();
        getPresenter().onPause();
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().onDestroy();
    }

    @Override
    public void notifyNetworkError(String msg) {
        notificationManager.show(msg, NotificationType.ERROR);
    }

    @Override
    public void notifyError(String msg) {
        notificationManager.show(msg, NotificationType.ERROR);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getPresenter().onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        getPresenter().onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void toggleProgress(boolean isShow) {
        toggleProgressDialog(isShow);
    }
}
