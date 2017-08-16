package cbsa.switchon.origination.common.presenter;

import android.os.Bundle;
import android.view.View;

public interface BasePresenter {
    void onCreate(Bundle savedInstanceState);
    void onCreateView(View parentView);
    void onViewCreated(Bundle savedInstanceState, Bundle arguments);
    void onResume();
    void onPause();
    void onDestroyView();
    void onDestroy();
    void onSaveInstanceState(Bundle outState);
    void onRestoreInstanceState(Bundle savedInstanceState);
}
