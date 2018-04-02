package com.igap.common.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.igap.R;
import com.igap.application.MyApplication;
import com.igap.application.prefs.AppPreference;
import com.igap.common.fragment.FragmentUtils;
import com.igap.common.widget.materialdialog.BaseMaterialDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.igap.common.utils.UIUtil.hideKeyboard;

public class BaseActivity extends AppCompatActivity { // NOSONAR

    private BaseMaterialDialog progressDialog;

    private Unbinder unbinder;

    private final int[] viewLocations = new int[2];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        onInitComponent();
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
            onPrepareView();
            unbinder = ButterKnife.bind(this);
        }
    }

    /**
     * Prepare view before start involve butter knife inject
     */
    protected void onPrepareView() {
        // Stub method
    }

    public void toggleProgressDialog(boolean isShow) {
        if (progressDialog == null) {
            progressDialog = new BaseMaterialDialog(this, R.layout.view_progress_dialog, true);
            progressDialog.getDialog().setOnCancelListener(dialog -> {
                dialog.dismiss();
                BaseActivity.this.finish();
            });
        }
        if (isShow) {
            progressDialog.showDialog();
        } else {
            progressDialog.hideDialog();
        }
    }

    protected void onInitComponent() {
        // Stub method
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (progressDialog != null) {
            progressDialog.hideDialog();
        }
    }

    protected int getLayoutId() {
        return 0;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();
        if (v != null &&
                (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.")) {
            v.getLocationOnScreen(viewLocations);
            float x = ev.getRawX() + v.getLeft() - viewLocations[0];
            float y = ev.getRawY() + v.getTop() - viewLocations[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom()) {
                hideKeyboard(this);
            }
        }
        return super.dispatchTouchEvent(ev);
    }


    protected void replaceFragment(int containerId, Fragment fragment) {
        replaceFragment(false, containerId, fragment);
    }

    protected void replaceFragment(boolean addToBackStack, int containerId, Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right);
        fragmentTransaction.replace(containerId, fragment, FragmentUtils.getDefaultFragmentTag(fragment.getClass()));
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    protected AppPreference getAppPreference() {
        return MyApplication.getAppComponent().appPreference();
    }
}
