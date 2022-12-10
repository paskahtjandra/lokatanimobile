package com.example.tapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Beranda extends AppCompatActivity implements View.OnClickListener {

    private ImageView buttonpasar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        buttonpasar= (ImageView) findViewById(R.id.btn_pasar);
        buttonpasar.setOnClickListener(this);
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