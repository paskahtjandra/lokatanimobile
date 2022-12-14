package com.example.tapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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

public class register extends AppCompatActivity implements View.OnClickListener {

    private Button buttonregister;
    private TextView buttonlogin;
    private EditText fullname, username, email, password, cfpassword;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        fullname = (EditText) findViewById(R.id.fullname);
        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        cfpassword = (EditText) findViewById(R.id.cfpassword);

        buttonlogin = (TextView) findViewById(R.id.login);
        buttonlogin.setOnClickListener(this);
        buttonregister= (Button) findViewById(R.id.btn_register);
        buttonregister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                createUser();
                break;
        }
        switch (v.getId()) {
            case R.id.login:
                startActivity(new Intent(this, Login.class));
                break;
        }
    }

    private void createUser(){

        String fullnames = fullname.getText().toString().trim();
        String usernames = username.getText().toString().trim();
        String emails = email.getText().toString().trim();
        String passwords = password.getText().toString().trim();
        String cfpasswords = cfpassword.getText().toString().trim();

        if(fullnames.isEmpty()){
            fullname.setError("Name is Required!");
            fullname.requestFocus();
            return;
        }

        if(usernames.isEmpty()){
            username.setError("Name is Required!");
            username.requestFocus();
            return;
        }

        if(emails.isEmpty()){
            email.setError("Email is Required!");
            email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emails).matches()){
            email.setError("Your Email is not Valid!");
            email.requestFocus();
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
        final Users user = new Users (fullname.getText().toString(),email.getText().toString(),"Indonesia",username.getText().toString(),
                password.getText().toString(),password.getText().toString(),"pembeli");
        Call<Users> call = ApiService.registerUser(user);

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
               if (response.body()!=null){
                   progressBar.setVisibility(View.INVISIBLE);
                   Toast.makeText(register.this, "Account Created", Toast.LENGTH_SHORT).show();
                   startActivity(new Intent(register.this, Login.class));
               }else{
                   progressBar.setVisibility(View.INVISIBLE);
                   Toast.makeText(register.this, response.body().geterror(), Toast.LENGTH_SHORT).show();
               }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(register.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

//        Users user = new Users("ayam","ayam@gmail.com","ayam","ayam","ayam123","pembeli");
//
//        Call<Users> call = ApiService.registerUser(user);
//
//        call.enqueue((new Callback<Users>() {
//            @Override
//            public void onResponse(Call<Users> call, Response<Users> response) {
//                if (!response.isSuccessful()) {
//                    Toast.makeText(register.this, "Account Created", Toast.LENGTH_SHORT).show();
//                    progressBar.setVisibility(View.GONE);
//                }else{
//                    Toast.makeText(register.this, "Failed to Create Account", Toast.LENGTH_SHORT).show();
//                    progressBar.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Users> call, Throwable t) {
//                Toast.makeText(register.this, "Failed to Create Account", Toast.LENGTH_SHORT).show();
//            }
//        }));
    }
}