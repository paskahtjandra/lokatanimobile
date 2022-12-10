package com.example.tapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class register extends AppCompatActivity implements View.OnClickListener {

    private Button buttonregister;
    private TextView buttonlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        buttonlogin = (TextView) findViewById(R.id.login);
        buttonlogin.setOnClickListener(this);

        buttonregister= (Button) findViewById(R.id.btn_register);
        buttonregister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                startActivity(new Intent(this, Beranda.class));
                break;
        }
        switch (v.getId()) {
            case R.id.login:
                startActivity(new Intent(this, Beranda.class));
                break;
        }
    }
}