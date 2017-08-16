package cbsa.switchon.origination.application.api.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseMapperResult implements Serializable {
    @SerializedName("api_code")
    private String apiCode; // Use to check generic exception (ie: invalid token)

    public void setApiCode(String code) {
        apiCode = code;
    }

    public String getApiCode() {
        return apiCode;
    }
}
