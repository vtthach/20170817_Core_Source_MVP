package cbsa.switchon.origination.application.properties;

import android.support.annotation.NonNull;
import android.text.TextUtils;

public class AppPropertyHelper {

    private static final String PROPERTY_ENDPOINT = "endpoint";
    private final AppProperties appProperties;

    public AppPropertyHelper(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public String getApiEndpoint() {
        String endPoint = appProperties.getProperty(PROPERTY_ENDPOINT, "");
        return getValidEndpoint(endPoint);
    }

    private String getValidEndpoint(String endpoint) {
        if (!TextUtils.isEmpty(endpoint) && notValidEndPoint(endpoint)) {
            return endpoint + "/";
        }
        return endpoint;
    }

    private boolean notValidEndPoint(@NonNull String endpoint) {
        return !endpoint.endsWith("/");
    }

    public int getMockMethod() {
        return 0;
    }
}
