package cbsa.switchon.origination.features.gettoken;

import javax.inject.Inject;

import cbsa.switchon.origination.common.presenter.BasePresenterImpl;
import cbsa.switchon.origination.features.gettoken.injection.GetTokenContractor;

public class GetTokenPresenterImpl extends BasePresenterImpl implements GetTokenContractor.GetTokenPresenter {

    private final GetTokenContractor.GetTokenView view;

    @Inject
    public GetTokenPresenterImpl(GetTokenContractor.GetTokenView view) {
        this.view = view;
    }

}