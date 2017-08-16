package cbsa.switchon.origination.features.gettoken.usecase;

import cbsa.switchon.origination.features.gettoken.model.GetTokenResult;

import io.reactivex.Observable;

public interface GetTokenUseCase {
    Observable<GetTokenResult> getGetToken();
}
