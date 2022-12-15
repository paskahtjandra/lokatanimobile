package com.example.tapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tapi.api.ApiConfig;
import com.example.tapi.api.ApiService;
import com.example.tapi.model.Produks;
import com.example.tapi.model.Users;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Beranda extends AppCompatActivity implements View.OnClickListener {

    private ImageView buttonpasar;
    private TextView name;
    private static String token;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    GridLayoutManager layoutManager;
    ProduksAdapter adapter;
    List<Produks> produksList = new ArrayList<>();

    Login login = new Login();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        buttonpasar= (ImageView) findViewById(R.id.btn_pasar);
        buttonpasar.setOnClickListener(this);
        name = (TextView) findViewById(R.id.name);

        //creating recyclerview
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ProduksAdapter(produksList);
        recyclerView.setAdapter(adapter);

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
        fetchPosts();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pasar:
                startActivity(new Intent(this, Pasar.class));
                break;
        }
    }

    private void fetchPosts(){
        progressBar.setVisibility(View.VISIBLE);
        ApiService ApiService = ApiConfig.getRetrofit().create(ApiService.class);
        Call<List<Produks>> call = ApiService.getProduks();
        call.enqueue(new Callback<List<Produks>>() {
            @Override
            public void onResponse(Call<List<Produks>> call, Response<List<Produks>> response) {
                if(response.isSuccessful()){
                    produksList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<Produks>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(Beranda.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}