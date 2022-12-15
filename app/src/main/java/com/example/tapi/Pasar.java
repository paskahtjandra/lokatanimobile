package com.example.tapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tapi.api.ApiConfig;
import com.example.tapi.api.ApiService;
import com.example.tapi.model.Produks;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pasar extends AppCompatActivity implements View.OnClickListener {

    private ImageView buttonberanda;

    RecyclerView recyclerView;
    ProgressBar progressBar;
    GridLayoutManager layoutManager;
    ProduksAdapter adapter;
    List<Produks> produksList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasar);

        buttonberanda= (ImageView) findViewById(R.id.btn_beranda);
        buttonberanda.setOnClickListener(this);

        //creating recyclerview
        recyclerView = (RecyclerView) findViewById(R.id.recyclerviews);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ProduksAdapter(produksList);
        recyclerView.setAdapter(adapter);

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
                Toast.makeText(Pasar.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_beranda:
                startActivity(new Intent(this, Beranda.class));
                break;
        }
    }
}