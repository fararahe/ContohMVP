package id.farless.contohmvp.activity.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import id.farless.contohmvp.R;
import id.farless.contohmvp.activity.MainActivity;
import id.farless.contohmvp.databinding.ActivityLoginBinding;
import id.farless.contohmvp.network.model.LoginModel;
import id.farless.contohmvp.network.response.LoginResponse;

public class LoginActivity extends AppCompatActivity implements LoginView{

    // Android View Binding
    private Context context;
    private ActivityLoginBinding binding;
    private LoginPresenter loginPresenter;
    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Cara membinding data dan menampilkan ke aplikasi
        binding     = ActivityLoginBinding.inflate(getLayoutInflater());
        View view   = binding.getRoot();
        setContentView(view);

        // setup presenter
        context         = this; // kalau tipenya Activity, context = this activity
        loginPresenter  = new LoginPresenter(context,this);

        // melakukan aksi ketika button login di click
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // panggil method checkLogin
                checkLogin();
            }
        });
    }

    @Override
    public void loginSuccess(LoginResponse response) {
        // data sukses akan di kirim ke activity selanjutnya
        Intent intent = new Intent(this, MainActivity.class);

        // 0 = false, artinya tidak error. ini hanya penanda saja
        intent.putExtra("error", "0");

        // passing model ke activity baru
        // 1. pakai cara Serializable, 2. pakai cara parcelable (contoh saat ini menggunakan cara 1)
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", response);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    @Override
    public void loginErrorResponse(String errorResponse) {
        // contoh, ketika error data error akan di kirim ke activity selanjutnya
        Intent intent = new Intent(this, MainActivity.class);

        // 1 = true, artinya terdapat error. ini hanya penanda saja
        intent.putExtra("error", "1");

        // passing message error (berupa string) ke activity baru
        intent.putExtra("message", errorResponse);

        startActivity(intent);
    }

    private void checkLogin(){
        // Cara memanggil element tampilan yang telah di binding
        email       = binding.tieEmail.getText().toString(); // mengambil text dari input text email
        password    = binding.tiePassword.getText().toString(); // mengambil text dari input text password

        if (email.isEmpty()){
            // jika email kosong
            binding.tieEmail.setError("wajib di isi");
        }else if (password.isEmpty()){
            // jika password kosong
            binding.tiePassword.setError("wajib di isi");
        }else{
            // email dan password sudah di isi
            loginPresenter.login(email, password);
        }
    }
}