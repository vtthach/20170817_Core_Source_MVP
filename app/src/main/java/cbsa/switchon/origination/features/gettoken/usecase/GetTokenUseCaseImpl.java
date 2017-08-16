package cbsa.switchon.origination.features.gettoken.usecase;


import javax.inject.Inject;

import cbsa.switchon.origination.application.api.BaseUseCase;
import cbsa.switchon.origination.features.gettoken.api.model.GetTokenRequest;
import cbsa.switchon.origination.features.gettoken.api.repository.GetTokenRepository;
import cbsa.switchon.origination.features.gettoken.model.GetTokenResult;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GetTokenUseCaseImpl extends BaseUseCase implements GetTokenUseCase {

    private final GetTokenRepository repository;

    @Inject
    public GetTokenUseCaseImpl(GetTokenRepository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<GetTokenResult> getGetToken() {
        return repository.getGetToken(getRequest())
                .map(GetTokenResult::transform)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    private GetTokenRequest getRequest() {
        GetTokenRequest request = new GetTokenRequest();
        // TODO add your
        return request;
    }

}
