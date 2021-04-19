package id.farless.contohmvp.activity.login;

import android.content.Context;

import id.farless.contohmvp.network.NetworkClient;
import id.farless.contohmvp.network.response.LoginResponse;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter implements LoginPresenterImp {

    LoginView view;
    Context context;

    public LoginPresenter(Context context, LoginView view) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void login(String email, String password) {
        final Observable<LoginResponse> loginAPi = NetworkClient.getNetworkClient(context)
                .login(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        DisposableObserver<LoginResponse> as = loginAPi.subscribeWith(new DisposableObserver<LoginResponse>(){
            @Override
            public void onNext(@NonNull LoginResponse response) {
                view.loginSuccess(response);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                view.loginErrorResponse(e.getMessage());
            }

            @Override
            public void onComplete() {
            }
        });
    }
}
