package id.farless.contohmvp.activity.login;

import id.farless.contohmvp.network.response.LoginResponse;

public interface LoginView {
    void loginSuccess(LoginResponse response);

    void loginErrorResponse(String errorResponse);
}
