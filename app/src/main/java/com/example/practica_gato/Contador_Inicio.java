package com.example.practica_gato;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.content.Intent;

public class Contador_Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contador_inicio);
    }

    CountDownTimer contador = new CountDownTimer (5000,1000) {

        @Override
        public void onTick(long l) {

        }

        @Override
        public void onFinish() {
            Intent intent = new Intent(Contador_Inicio.this, MainActivity.class);
            startActivity(intent);
        }
    }.start();
}