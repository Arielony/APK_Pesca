package com.example.acer.pescaa.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.acer.pescaa.R;
import com.example.acer.pescaa.model.UsuarioDAO;


public class RegistrarActivity extends AppCompatActivity {

    private Button btnRegistro;
    private EditText txtUsername;
    private EditText txtPass1;
    private EditText txtPass2;
    private EditText txtEmail;
    private EditText txtRut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar_layout);

        // Views
        txtUsername = (EditText) findViewById(R.id.registrar_txt_username);
        txtPass1 = (EditText) findViewById(R.id.registrar_txt_password1);
        txtPass2 = (EditText) findViewById(R.id.registrar_txt_password2);
        txtEmail = (EditText) findViewById(R.id.registrar_txt_email);
        txtRut = (EditText) findViewById(R.id.registrar_txt_rut);
        btnRegistro = (Button) findViewById(R.id.registrar_btn_registrar);
    }


    public void registrar(View v) {
        if(validarRegistro()) {
            if(validarPassword()) {
                if(registrarUsuario()) {
                    finish(); // Se cierra esta actividad porque el parent es MainActivity
                }
            }
        } else {
            Toast.makeText(this, "Faltan datos!", Toast.LENGTH_SHORT).show();
        }
    }



    private boolean registrarUsuario() {
        UsuarioDAO user = new UsuarioDAO(this);

        if(user.existeUsuario(txtUsername.getText().toString())) {
            Toast.makeText(this, "Usuario " + txtUsername.getText().toString() + " ya existe..", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return user.insertarUsuario(txtUsername.getText().toString(), txtPass1.getText().toString(), txtEmail.getText().toString(), txtRut.getText().toString());
        }

    }


    private boolean validarRegistro() {

        if(txtUsername.getText().toString().isEmpty())
            return false;

        if(txtPass1.getText().toString().isEmpty())
            return false;

        if(txtPass2.getText().toString().isEmpty())
            return false;

        if(txtRut.getText().toString().isEmpty())
            return false;

        if(txtEmail.getText().toString().isEmpty())
            return false;

        return true;
    }


    private boolean validarPassword() {
        if(txtPass1.getText().toString().equalsIgnoreCase(txtPass2.getText().toString())){
            return true;
        } else {
            Toast.makeText(this, "Contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
