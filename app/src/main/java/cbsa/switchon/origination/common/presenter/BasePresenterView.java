package cbsa.switchon.origination.common.presenter;

import android.content.Context;

public interface BasePresenterView {
    void toggleProgress(boolean isShow);
    void notifyNetworkError(String msg);
    void notifyError(String msg);
    Context getAppContext();
}
