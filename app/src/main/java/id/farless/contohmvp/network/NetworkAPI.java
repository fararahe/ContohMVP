package id.farless.contohmvp.network;

import id.farless.contohmvp.network.response.LoginResponse;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface NetworkAPI {
    @FormUrlEncoded
    @POST("app/v1/auth.php?m=login")
    Observable<LoginResponse> login(@Field("email") String email,
                                    @Field("password") String password);
}
