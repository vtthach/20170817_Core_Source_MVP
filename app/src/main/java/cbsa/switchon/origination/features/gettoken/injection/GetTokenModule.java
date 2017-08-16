package cbsa.switchon.origination.features.gettoken.injection;


import com.amb.retrofitwrapper.RetrofitConstants;

import javax.inject.Named;

import cbsa.switchon.origination.application.injection.quanlifier.PerView;
import cbsa.switchon.origination.features.gettoken.GetTokenPresenterImpl;
import cbsa.switchon.origination.features.gettoken.api.repository.GetTokenApiService;
import cbsa.switchon.origination.features.gettoken.api.repository.GetTokenRepository;
import cbsa.switchon.origination.features.gettoken.api.repository.GetTokenRepositoryImpl;
import cbsa.switchon.origination.features.gettoken.usecase.GetTokenUseCase;
import cbsa.switchon.origination.features.gettoken.usecase.GetTokenUseCaseImpl;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


@Module
@PerView
public class GetTokenModule {
    private GetTokenContractor.GetTokenView view;

    public GetTokenModule(GetTokenContractor.GetTokenView view) {
        this.view = view;
    }

    @Provides
    @PerView
    GetTokenContractor.GetTokenView provideView() {
        return view;
    }

    @Provides
    @PerView
    GetTokenContractor.GetTokenPresenter provideSignUpPresenter(GetTokenPresenterImpl presenter) {
        return presenter;
    }

    @Provides
    @PerView
    public GetTokenRepository provideGetTokenRepository(GetTokenRepositoryImpl repository) {
        return repository;
    }

    @Provides
    @PerView
    public GetTokenApiService provideGetTokenApiService(@Named(RetrofitConstants.RETROFIT_IGNORE_CERTIFICATE) Retrofit retrofit) {
        return retrofit.create(GetTokenApiService.class);
    }

    @Provides
    @PerView
    public GetTokenUseCase provideGetTokenUseCase(GetTokenUseCaseImpl useCase) {
        return useCase;
    }
}
