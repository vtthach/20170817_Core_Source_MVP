package cbsa.switchon.origination.features.gettoken.injection;


import cbsa.switchon.origination.application.injection.AppComponent;
import cbsa.switchon.origination.application.injection.quanlifier.PerView;
import cbsa.switchon.origination.features.gettoken.GetTokenFragment;

import dagger.Component;

@PerView
@Component(dependencies = AppComponent.class, modules = {GetTokenModule.class})
public interface GetTokenComponent {
    void inject(GetTokenFragment fragment);
}
