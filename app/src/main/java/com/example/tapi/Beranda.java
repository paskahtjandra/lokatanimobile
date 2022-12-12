package com.example.tapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tapi.api.ApiConfig;
import com.example.tapi.api.ApiService;
import com.example.tapi.model.Users;

import org.w3c.dom.Text;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Beranda extends AppCompatActivity implements View.OnClickListener {

    private ImageView buttonpasar;
    private TextView name;
    private static String token;

    Login login = new Login();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        buttonpasar= (ImageView) findViewById(R.id.btn_pasar);
        buttonpasar.setOnClickListener(this);
        name = (TextView) findViewById(R.id.name);


        ApiService ApiService = ApiConfig.getRetrofit().create(ApiService.class);
        token ="bearer "+ login.token;
        Call<Users> call = ApiService.getName(token);

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()){
                    name.setText(response.body().getNickname());
                    Toast.makeText(Beranda.this, "Successful", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Beranda.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(Beranda.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                Toast.makeText(Beranda.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pasar:
                startActivity(new Intent(this, Pasar.class));
                break;
        }
    }
}