package cbsa.switchon.origination.features.gettoken.api.repository;

import javax.inject.Inject;

import cbsa.switchon.origination.application.api.BaseRepositoryImpl;
import cbsa.switchon.origination.features.gettoken.api.model.GetTokenRequest;
import cbsa.switchon.origination.features.gettoken.api.model.GetTokenResponse;
import io.reactivex.Observable;
import retrofit2.http.Body;

public class GetTokenRepositoryImpl extends BaseRepositoryImpl<GetTokenApiService> implements GetTokenRepository {
    @Inject
    public GetTokenRepositoryImpl(GetTokenApiService service) {
        super(service);
    }

    @Override
    public Observable<GetTokenResponse> getGetToken(@Body GetTokenRequest request) {
        return service.getGetToken(request);
    }
}
