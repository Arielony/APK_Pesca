package com.example.acer.pescaa.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.acer.pescaa.R;
import com.example.acer.pescaa.db.DBHelper;
import com.example.acer.pescaa.model.ProductoDAO;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {

    private ListView listProductos;
    private ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado_layout);


        // Views
        listProductos = (ListView) findViewById(R.id.listado_list_productos);


        ProductoDAO pdao = new ProductoDAO(this);

        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_2, android.R.id.text1, pdao.getAllProductos());
        listProductos.setAdapter(adapter);

        listProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Enviamos informacion al intent
                Intent i = new Intent(ListadoActivity.this, ProductosActivity.class);
                i.putExtra("ID", position + 1);
                startActivity(i);
            }
        });
    }
}
