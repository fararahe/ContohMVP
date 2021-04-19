package id.farless.contohmvp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import id.farless.contohmvp.R;
import id.farless.contohmvp.databinding.ActivityLoginBinding;
import id.farless.contohmvp.databinding.ActivityMainBinding;
import id.farless.contohmvp.network.response.LoginResponse;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding     = ActivityMainBinding.inflate(getLayoutInflater());
        View view   = binding.getRoot();
        setContentView(view);

        // ambil data yang sudah di passing dari Intent
        Intent intent = getIntent();

        if (intent.getStringExtra("error").equals("0")){
            // error == 0 (penanda tidak error)
            LoginResponse loginResponse = (LoginResponse) intent.getExtras().getSerializable("data");
            String token                = "Token login: " + loginResponse.getToken();
            binding.tvContent.setText(token);
        }else{
            // error == 1 (penanda terdapat error)
            String errorResponse        = "Error response: " + intent.getStringExtra("message");
            binding.tvContent.setText(errorResponse);
        }

    }
}