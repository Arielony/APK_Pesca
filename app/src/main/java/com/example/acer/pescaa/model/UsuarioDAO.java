package com.example.acer.pescaa.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.acer.pescaa.db.DBHelper;

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


    public boolean loguearUsuario(String username, String password) {
        SQLiteDatabase db = DBHelper.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE + " WHERE " + COLUMN_USERNAME + " = " + username + " AND " + COLUMN_PASSWORD + " = " + password, null);

        return (res.getCount() > 0);
    }


    public boolean existeUsuario(String username) {

        SQLiteDatabase db = DBHelper.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE + " WHERE " + COLUMN_USERNAME + " = " + username, null);

        return (res.getCount() > 0);

    }


    public int numberOfRows() {
        SQLiteDatabase db = DBHelper.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE);
        return numRows;
    }



}
