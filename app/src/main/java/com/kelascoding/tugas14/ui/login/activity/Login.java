package com.kelascoding.tugas14.ui.login.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.textfield.TextInputLayout;
import com.kelascoding.tugas14.R;
import com.kelascoding.tugas14.rest.ApiConfig;
import com.kelascoding.tugas14.rest.ApiService;
import com.kelascoding.tugas14.ui.activity.MainActivity;
import com.kelascoding.tugas14.ui.login.model.User;
import com.kelascoding.tugas14.ui.login.sharedprefrences.SharedPref;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    TextInputLayout edtUsername, edtPass;
    AppCompatButton btnLogin;
    TextView tvSignUp;
    Vibrator vibrator;
    SharedPreferences sharedPreferences;

    //change this to match your url
    final String loginURL = "https://restapitugas14.000webhostapp.com/user/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.edtUsername);
        edtPass = findViewById(R.id.edtPass);
        btnLogin = findViewById(R.id.btn_login);
        tvSignUp = findViewById(R.id.tvSignUp);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        btnLogin.setOnClickListener(view -> {

            validateUserData();
        });

        tvSignUp.setOnClickListener(v -> {
            Intent login = new Intent(getApplicationContext(), Register.class);
            startActivity(login);
        });

    }

    private void validateUserData() {
        final String username = String.valueOf(edtUsername.getEditText().getText());
//        final String email = String.valueOf(edtEmail.getEditText().getText());
        final String password = String.valueOf(edtPass.getEditText().getText());

        //checking if username is empty
        if (TextUtils.isEmpty(username)) {
            edtUsername.setError("Please enter your username");
            edtUsername.requestFocus();
            // Vibrate for 100 milliseconds
            vibrator.vibrate(100);
            btnLogin.setEnabled(true);
            return;
        }

        //checking if password is empty
        if (TextUtils.isEmpty(password)) {
            edtPass.setError("Please enter your password");
            edtPass.requestFocus();
            //Vibrate for 100 milliseconds
            vibrator.vibrate(100);
            btnLogin.setEnabled(true);
            return;
        }

        loginUser(username,password);
    }

    private void loginUser(String username, String password) {
        ApiService apiService = ApiConfig.getApiService();
        Call<User> login = apiService.login(username, password);
        login.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().getSuccess() == 1) {
                    String user = response.body().getUsername();
                    SharedPref.getInstance(Login.this).storeUser(user);
//                    SharedPref.getInstance(Login.this).storeEmail(email);
                    Toast.makeText(Login.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, MainActivity.class));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(Login.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


}
