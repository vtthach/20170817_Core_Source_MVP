package cbsa.switchon.origination.features.gettoken;


import android.support.annotation.NonNull;

import javax.inject.Inject;

import cbsa.switchon.origination.R;
import cbsa.switchon.origination.application.MyApplication;
import cbsa.switchon.origination.common.fragment.BasePresenterFragment;
import cbsa.switchon.origination.features.gettoken.injection.DaggerGetTokenComponent;
import cbsa.switchon.origination.features.gettoken.injection.GetTokenContractor;
import cbsa.switchon.origination.features.gettoken.injection.GetTokenModule;

public class GetTokenFragment extends BasePresenterFragment<GetTokenContractor.GetTokenPresenter> implements GetTokenContractor.GetTokenView {

    @Inject
    GetTokenContractor.GetTokenPresenter presenter;

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
