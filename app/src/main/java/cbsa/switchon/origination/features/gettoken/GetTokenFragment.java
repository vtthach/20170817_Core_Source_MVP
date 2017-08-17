package cbsa.switchon.origination.features.gettoken;


import android.app.Activity;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import cbsa.switchon.origination.R;
import cbsa.switchon.origination.application.MyApplication;
import cbsa.switchon.origination.common.activity.ContainerActivity;
import cbsa.switchon.origination.common.fragment.BasePresenterFragment;
import cbsa.switchon.origination.features.gettoken.injection.DaggerGetTokenComponent;
import cbsa.switchon.origination.features.gettoken.injection.GetTokenContractor;
import cbsa.switchon.origination.features.gettoken.injection.GetTokenModule;

public class GetTokenFragment extends BasePresenterFragment<GetTokenContractor.GetTokenPresenter> implements GetTokenContractor.GetTokenView {

    @Inject
    GetTokenContractor.GetTokenPresenter presenter;

    public static void showMe(Activity activity) {
        ContainerActivity.IntentBuilder intentBuilder = new ContainerActivity.IntentBuilder(activity)
                .setFragmentClass(GetTokenFragment.class);
        intentBuilder.start();
    }

    @Override
    protected void onInitComponent() {
        super.onInitComponent();
        DaggerGetTokenComponent.builder()
                .appComponent(MyApplication.getAppComponent())
                .getTokenModule(new GetTokenModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.get_token_fragment;
    }

    @NonNull
    @Override
    protected GetTokenContractor.GetTokenPresenter getPresenter() {
        return presenter;
    }
}
