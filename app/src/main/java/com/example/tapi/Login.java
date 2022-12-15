package com.example.tapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tapi.api.ApiConfig;
import com.example.tapi.api.ApiService;
import com.example.tapi.model.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button buttonlogin;
    private TextView buttonregister;
    private EditText username, password;
    private ProgressBar progressBar;
    Users user = new Users();
    public static String token = "a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        buttonlogin = (Button) findViewById(R.id.btn_login);
        buttonlogin.setOnClickListener(this);

        buttonregister= (TextView) findViewById(R.id.register);
        buttonregister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                login();
                break;
        }
        switch (v.getId()) {
            case R.id.register:
                startActivity(new Intent(this, register.class));
                break;
        }
    }

    private void login(){

        String usernames = username.getText().toString().trim();
        String passwords = password.getText().toString().trim();

        if(usernames.isEmpty()){
            username.setError("Name is Required!");
            username.requestFocus();
            return;
        }

        if(passwords.isEmpty()){
            password.setError("Password is Required!");
            password.requestFocus();
            return;
        }

        if(password.length()<6){
            password.setError("Password Must Be 6 Characters Long!");
            password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        ApiService ApiService = ApiConfig.getRetrofit().create(ApiService.class);
        final Users user = new Users (username.getText().toString(),password.getText().toString());
        Call<Users> call = ApiService.userLogin(user);

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()){
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
//                    user.setToken(response.body().getToken());
                    token = response.body().getToken();
                    startActivity(new Intent(Login.this, Beranda.class));
                }else{
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(Login.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(Login.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}