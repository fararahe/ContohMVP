package id.farless.contohmvp.network;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import id.farless.contohmvp.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient extends Application {

    public static NetworkAPI getNetworkClient(Context context){
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(NetworkAPI.class);
    }

    public static OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .addInterceptor(getLoggingInterceptor())
                .build();
    }

    public static HttpLoggingInterceptor.Level getInterceptorLevel() {
        if (BuildConfig.DEBUG)
            return HttpLoggingInterceptor.Level.BODY;
        else
            return HttpLoggingInterceptor.Level.NONE;
    }

    public static HttpLoggingInterceptor getLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(getInterceptorLevel());
        return httpLoggingInterceptor;
    }
}
