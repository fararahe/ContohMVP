package id.farless.contohmvp.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginResponse implements Serializable {
    @SerializedName("error_code")
    @Expose
    private String errorCode;

    @SerializedName("error_desc")
    @Expose
    private String errorDesc;

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("expire_at")
    @Expose
    private String expireAt;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(String expireAt) {
        this.expireAt = expireAt;
    }
}
