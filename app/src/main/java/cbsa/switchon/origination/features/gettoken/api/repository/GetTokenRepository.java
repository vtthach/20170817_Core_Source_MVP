package cbsa.switchon.origination.features.gettoken.api.repository;

import cbsa.switchon.origination.features.gettoken.api.model.GetTokenRequest;
import cbsa.switchon.origination.features.gettoken.api.model.GetTokenResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;

public interface GetTokenRepository {
    Observable<GetTokenResponse> getGetToken(@Body GetTokenRequest request);
}
