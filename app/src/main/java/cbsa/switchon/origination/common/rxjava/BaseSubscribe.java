package cbsa.switchon.origination.common.rxjava;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import com.google.common.base.Strings;

import cbsa.switchon.origination.application.api.exception.ApiException;
import cbsa.switchon.origination.common.presenter.BasePresenterView;
import cbsa.switchon.origination.common.utils.CommonUtils;
import io.reactivex.observers.DisposableObserver;
import timber.log.Timber;

public abstract class BaseSubscribe<T> extends DisposableObserver<T> {

    protected String getDebugErrorMessage(@NonNull Throwable e) {
        String msgError = e.getMessage();
        if (e instanceof ApiException) {
            msgError = ((ApiException) e).getMsg();
        }
        return Strings.isNullOrEmpty(msgError) ? e.getClass().getName() : msgError;
    }

    @Override
    public void onNext(@NonNull T result) {
        // Stub method to override
    }

    @Override
    public void onError(@NonNull Throwable e) {
        Timber.e("BaseSubscribe - onError: " + e.getMessage());
    }

    @Override
    public void onComplete() {
        // Stub method to override
    }

    /**
     * Auto toogle progress loading for this subscriber
     *
     * @param <T> instance
     */
    public abstract static class BasePresenterSubscriber<T> extends ApiSubscriber<T> {

        private final boolean autoToggleProgress;

        BasePresenterView view;

        public BasePresenterSubscriber(BasePresenterView view) {
            this(true, view);
        }

        public BasePresenterSubscriber(boolean autoToggleProgress, BasePresenterView view) {
            this.view = view;
            this.autoToggleProgress = autoToggleProgress;
        }

        @Override
        @CallSuper
        protected void onStart() {
            super.onStart();
            if (autoToggleProgress) {
                view.toggleProgress(true);
            }
        }

        @Override
        @CallSuper
        public void onComplete() {
            super.onComplete();
            if (autoToggleProgress) {
                view.toggleProgress(false);
            }
        }

        @Override
        @CallSuper
        public void onError(@NonNull Throwable e) {
            super.onError(e);
            if (autoToggleProgress) {
                view.toggleProgress(false);
            }
        }

        @Override
        protected void onCommonError(Throwable e) {
            view.notifyError(getDebugErrorMessage(e));
        }

        @Override
        protected void onNetworkError(Throwable e) {
            view.notifyError(getDebugErrorMessage(e));
        }
    }


    /**
     * Auto toogle progress loading for this subscriber
     *
     * @param <T> instance
     */
    public abstract static class ApiSubscriber<T> extends BaseSubscribe<T> {

        protected void onCommonError(Throwable e) {
            // Stub method
        }

        protected void onNetworkError(Throwable e) {
            // Stub method
        }

        @Override
        @CallSuper
        public void onError(@NonNull Throwable e) {
            super.onError(e);
            if (CommonUtils.isNetworkError(e)) {
                onNetworkError(e);
            } else {
                onCommonError(e);
            }
        }
    }
}