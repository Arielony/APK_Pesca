package com.example.acer.pescaa.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.pescaa.R;
import com.example.acer.pescaa.model.ProductoDAO;

public class ProductosActivity extends AppCompatActivity {


    private ImageView imgProducto;
    private TextView nombre, precio;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productos_layout);

        // Views
        nombre = (TextView) findViewById(R.id.producto_txt_nombre);
        precio = (TextView) findViewById(R.id.producto_txt_precio);



        // Leer el producto
        int idProducto = getIntent().getExtras().getInt("ID");

        if(idProducto < 1 ) {
            Toast.makeText(this, "No fue posible cargar el producto!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }


        ProductoDAO pdao = new ProductoDAO(this);
        Cursor prod = pdao.getProductoById(idProducto);


        // Rellenar los campos
        if(prod != null) {
            prod.moveToFirst();
          //  nombre.setText(prod.getString(prod.getColumnIndex(ProductoDAO.COLUMN_NOMBRE)));
          //  precio.setText("$ " + prod.getInt(prod.getColumnIndex(ProductoDAO.COLUMN_PRECIO)));

            nombre.setText(prod.getString(1));
            precio.setText("$ " + prod.getInt(2));


            Log.i("PROD", prod.getString(1));
            Log.i("PROD", prod.getString(2));
            Log.i("PROD", prod.getString(3));

            // Cargar imagen
            Resources res = getResources();
            String mDrawableName = prod.getString(3);
            int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
       //     Drawable drawable = res.getDrawable(resID);
       //     imgProducto.setImageDrawable(drawable);

        } else {
            Toast.makeText(this, "No fue posible cargar el producto!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

    }



    public void comprar(View v) {
        Toast.makeText(this, "Comprado!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
