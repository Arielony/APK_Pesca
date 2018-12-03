package com.example.acer.pescaa.model;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.acer.pescaa.activities.DrawerActivity;
import com.example.acer.pescaa.activities.MainActivity;
import com.example.acer.pescaa.db.DBHelper;

import org.json.JSONException;
import org.json.JSONObject;

public class UsuarioDAO {


    public static final String TABLE = "USUARIOS";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_USERNAME = "USERNAME";
    public static final String COLUMN_PASSWORD = "PASSWORD";
    public static final String COLUMN_EMAIL = "EMAIL";
    public static final String COLUMN_RUT = "RUT";
    private DBHelper DBHelper;
    private Context contexto;


    public UsuarioDAO(Context context) {
        DBHelper = DBHelper.getInstance(context);
        contexto = context;
    }


    public boolean insertarUsuario(String username, String password, String email, String rut) {

        SQLiteDatabase db = DBHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME, username);
        contentValues.put(COLUMN_PASSWORD, password);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_RUT, rut);


        boolean isAdded = (db.insert(TABLE, null, contentValues) != -1);

        if(isAdded)
            Toast.makeText(contexto, "Usuario ingresado!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(contexto, "Usuario no ingresado!", Toast.LENGTH_SHORT).show();

        return isAdded;
    }

    public Cursor getUsuarioById(int id) {
        SQLiteDatabase db = DBHelper.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE + " WHERE " + COLUMN_ID + " = " + id, null);
        return res;
    }


    public boolean loguearUsuario(final String username, String password, final Context con, final Button btnLogin) {


        btnLogin.setText("Iniciando ...");

        RequestQueue queue = Volley.newRequestQueue(contexto);


        String url ="https://saargo.com/api/login.php?USER=" + username + "&PASSWORD=" + password;

        // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                               // mTextView.setText("Response is: "+ response.substring(0,500));
                                Log.i("I", response);
                                Toast.makeText(con, response, Toast.LENGTH_LONG);

                                JSONObject json = null;
                                try {
                                    json = new JSONObject(response);

                                    Intent i = new Intent(con, DrawerActivity.class);
                                    i.putExtra("USER", json.getString("USERNAME"));
                                    i.putExtra("EMAIL", json.getString("EMAIL"));
                                    con.startActivity(i);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    btnLogin.setText("Iniciar Sesión");
                                    return;
                                }



                                btnLogin.setText("Iniciar Sesión");

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            //    mTextView.setText("That didn't work!");
                                Toast.makeText(con, "Error: " + error.getMessage(), Toast.LENGTH_LONG);
                                btnLogin.setText("Iniciar Sesión");
                            }
                    });

// Add the request to the RequestQueue.
        queue.add(stringRequest);




/*


        SQLiteDatabase db = DBHelper.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE + " WHERE " + COLUMN_USERNAME + " = '" + username + "' AND " + COLUMN_PASSWORD + " = '" + password + "'", null);

        return (res.getCount() > 0); */
        return false;
    }


    public boolean existeUsuario(String username) {

        SQLiteDatabase db = DBHelper.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE + " WHERE " + COLUMN_USERNAME + " = '" + username + "'", null);

        return (res.getCount() > 0);

    }


    public int numberOfRows() {
        SQLiteDatabase db = DBHelper.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE);
        return numRows;
    }



}
