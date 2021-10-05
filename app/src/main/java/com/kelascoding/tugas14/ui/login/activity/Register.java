package com.kelascoding.tugas14.ui.login.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.textfield.TextInputLayout;
import com.kelascoding.tugas14.R;
import com.kelascoding.tugas14.rest.ApiConfig;
import com.kelascoding.tugas14.rest.ApiService;
import com.kelascoding.tugas14.ui.login.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    TextInputLayout edtRegEmail, edtRegPass, edtRegUsername, edtRegNama;
    AppCompatButton btnRegister;
    Vibrator v;
    //change to your register url
    final String registerUrl = "https://restapitugas14.000webhostapp.com/user/regist.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edtRegEmail = findViewById(R.id.edtEmailReg);
        edtRegNama = findViewById(R.id.edtNameReg);
        edtRegUsername = findViewById(R.id.edtUsernameReg);
        edtRegPass = findViewById(R.id.edtPassReg);
        btnRegister = findViewById(R.id.btn_regist);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        btnRegister.setOnClickListener(v -> {
            validateUserData();
        });

    }

    private void validateUserData() {
        //find values
        final String reg_name = String.valueOf(edtRegNama.getEditText().getText());
        final String reg_email = String.valueOf(edtRegEmail.getEditText().getText());
        final String reg_username = String.valueOf(edtRegUsername.getEditText().getText());
        final String reg_password = String.valueOf(edtRegPass.getEditText().getText());

        if (TextUtils.isEmpty(reg_name)) {
            edtRegNama.setError("Please Enter Your Name");
            edtRegNama.requestFocus();
            v.vibrate(100);
            return;
        }

        if (TextUtils.isEmpty(reg_email)) {
            edtRegEmail.setError("Please Enter Your Email");
            edtRegEmail.requestFocus();
            v.vibrate(100);
            return;
        }
        if (TextUtils.isEmpty(reg_username)) {
            edtRegUsername.setError("Please Enter Your Email");
            edtRegUsername.requestFocus();
            v.vibrate(100);
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(reg_email).matches()) {
            edtRegEmail.setError("Enter a valid email");
            edtRegEmail.requestFocus();
            //Vibrate for 100 milliseconds
            v.vibrate(100);
            return;
        }
        if (TextUtils.isEmpty(reg_password)) {
            edtRegPass.setError("Please Enter Your Password");
            edtRegPass.requestFocus();
            v.vibrate(100);
            return;
        }

        //After Validating we register User
        registerUser(reg_name,reg_email,reg_username,reg_password);

    }

    private void registerUser(String name, String email, String username, String pass) {
        //find values
        final String reg_name = String.valueOf(edtRegNama.getEditText().getText());
        final String reg_email = String.valueOf(edtRegEmail.getEditText().getText());
        final String reg_username = String.valueOf(edtRegUsername.getEditText().getText());
        final String reg_password = String.valueOf(edtRegPass.getEditText().getText());

        ApiService apiService = ApiConfig.getApiService();
        Call<User> Register = apiService.register(name, email, username, pass);
        Register.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().getSuccess()==1) {
                    String user = response.body().getUsername();
                    Toast.makeText(Register.this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register.this,Login.class));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(Register.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}