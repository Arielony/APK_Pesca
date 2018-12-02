package com.example.acer.pescaa.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.acer.pescaa.R;

public class MenuActivity extends AppCompatActivity {

      private Button btnVerProductos;
      private Button btnSalir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        btnVerProductos = (Button) findViewById(R.id.menu_btn_productos);
        btnSalir = (Button) findViewById(R.id.menu_btn_exit);

    }



    public void verListado(View v) {
        Intent i = new Intent(MenuActivity.this, ListadoActivity.class);
        startActivity(i);
    }


    public void salir(View v) {
        Intent i = new Intent(MenuActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
