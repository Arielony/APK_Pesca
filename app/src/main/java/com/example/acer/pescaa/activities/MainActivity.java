package com.example.acer.pescaa.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.acer.pescaa.R;
import com.example.acer.pescaa.db.DBHelper;
import com.example.acer.pescaa.model.ProductoDAO;
import com.example.acer.pescaa.model.UsuarioDAO;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnRegistrar;
    private EditText txtUsername;
    private EditText txtPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        // Views
        txtUsername = (EditText) findViewById(R.id.main_txt_username);
        txtPassword = (EditText) findViewById(R.id.main_txt_password);
        btnLogin = (Button) findViewById(R.id.main_btn_login);
        btnRegistrar = (Button) findViewById(R.id.main_btn_registrar);


        // IMPRIME INFO BASE DE DATOS
        UsuarioDAO as = new UsuarioDAO(this);
        ProductoDAO pr = new ProductoDAO(this);

        Log.i("USUARIOS", "REGISTROS: " + as.numberOfRows());
        Log.i("PRODUCTOS", "REGISTROS: " + pr.numberOfRows());

    }


    public void IrRegistro(View v) {
        Intent i = new Intent(MainActivity.this, RegistrarActivity.class);
        startActivity(i);
    }

    public void login(View v) {
        if(validarLogin()) {
            Intent i = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(i);
            finish();
        }
    }



    private boolean validarLogin() {

        if(txtUsername.getText().toString().isEmpty() || txtPassword.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "Faltan datos!", Toast.LENGTH_SHORT).show();
            return false;
        }


        UsuarioDAO udao = new UsuarioDAO(this);

        if(udao.loguearUsuario(txtUsername.getText().toString(), txtPassword.getText().toString())) {
            Toast.makeText(MainActivity.this, "Ingreso éxitoso!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(MainActivity.this, "Credenciales incorrectas!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}