package cbsa.switchon.origination.common.presenter;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.view.View;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenterImpl implements BasePresenter {

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    @CallSuper
    public void onCreate(Bundle savedInstanceState) {
        // Stub method to override
    }

    @Override
    @CallSuper
    public void onCreateView(View parentView) {
        // Stub method to override
    }

    @Override
    @CallSuper
    public void onDestroyView() {
        // Stub method to override
    }

    @Override
    @CallSuper
    public void onDestroy() {
        // Stub method to override
        compositeDisposable.dispose();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Stub method to override
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Stub method to override
    }

    @Override
    @CallSuper
    public void onViewCreated(Bundle savedInstanceState, Bundle arguments) {
        // Stub method to override
    }

    @Override
    @CallSuper
    public void onResume() {
        // Stub method to override
    }

    @Override
    @CallSuper
    public void onPause() {
        // Stub method to override
    }

    protected String getDebugErrorMessage(@NonNull Throwable e) {
        return e.getMessage();
    }
}
