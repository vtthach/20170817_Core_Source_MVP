package com.igap.common.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.igap.R;
import com.igap.common.activity.BaseActivity;
import com.igap.common.presenter.BasePresenter;
import com.igap.common.presenter.BasePresenterView;


public abstract class BasePresenterFragment<T extends BasePresenter> extends BaseFragment implements BasePresenterView {

    @NonNull
    protected abstract T getPresenter();

    @Override
    @CallSuper
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenter().onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getPresenter().onSaveInstanceState(outState);
    }

    @Nullable
    @Override
    @CallSuper
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        getPresenter().onCreateView(view);
        return view;
    }

    @Override
    @CallSuper
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPresenter().onViewCreated(savedInstanceState, getArguments());
    }

    @Override
    @CallSuper
    public void onDestroyView() {
        super.onDestroyView();
        getPresenter().onDestroyView();
    }

    @Override
    @CallSuper
    public void onResume() {
        super.onResume();
        getPresenter().onResume();
    }

    @Override
    @CallSuper
    public void onPause() {
        super.onPause();
        getPresenter().onPause();
    }

    @Override
    @CallSuper
    public void onDestroy() {
        super.onDestroy();
        getPresenter().onDestroy();
    }

    @Override
    public void notifyNetworkError(String msg) {
        showToastError(getString(R.string.notify_no_internet_connection) + "[" + msg + "]");
    }

    @Override
    public void notifyError(String msg) {
        showToastError(msg);
    }

    @Override
    public void toggleProgress(boolean isShow) {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).toggleProgressDialog(isShow);
        }
    }

    @Override
    public Context getAppContext() {
        return getActivity().getApplicationContext();
    }
}
