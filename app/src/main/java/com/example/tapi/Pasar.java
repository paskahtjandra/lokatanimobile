package com.example.tapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class Pasar extends AppCompatActivity implements View.OnClickListener {

    private ImageView buttonberanda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasar);

        buttonberanda= (ImageView) findViewById(R.id.btn_beranda);
        buttonberanda.setOnClickListener(this);

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