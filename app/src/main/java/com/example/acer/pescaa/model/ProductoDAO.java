package com.example.acer.pescaa.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.acer.pescaa.db.DBHelper;

import java.util.ArrayList;

public class ProductoDAO {


    public static final String TABLE = "PRODUCTOS";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NOMBRE = "NOMBRE";
    public static final String COLUMN_PRECIO = "PRECIO";
    public static final String COLUMN_IMAGEN = "IMAGEN";
    private DBHelper DBHelper;
    private Context contexto;


    public ProductoDAO(Context context) {
        DBHelper = DBHelper.getInstance(context);
        contexto = context;
    }


    public boolean insertarProducto(String nombre, int precio, String imagen) {

        SQLiteDatabase db = DBHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NOMBRE, nombre);
        contentValues.put(COLUMN_PRECIO, precio);
        contentValues.put(COLUMN_IMAGEN, imagen);


        boolean isAdded = (db.insert(TABLE, null, contentValues) != -1);

        if(isAdded)
            Toast.makeText(contexto, "Producto ingresado!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(contexto, "Producto no ingresado!", Toast.LENGTH_SHORT).show();

        return isAdded;
    }

    public Cursor getProductoById(int id) {
        SQLiteDatabase db = DBHelper.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE + " WHERE " + COLUMN_ID + " = " + id, null);
        return res;
    }


    public ArrayList<String> getAllProductos() {
        ArrayList<String> array_list = new ArrayList<>();

        SQLiteDatabase db = DBHelper.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM PRODUCTOS", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {

            array_list.add(res.getString(res.getColumnIndex(COLUMN_NOMBRE)));
            res.moveToNext();
        }
        return array_list;
    }
/*
    public ArrayList<String[]> getAllProductos() {
        ArrayList<String[]> array_list = new ArrayList<>();

        SQLiteDatabase db = DBHelper.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM PRODUCTOS", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {

            String[] data = new String[4];

            data[0] = res.getString(res.getColumnIndex(COLUMN_ID));
            data[1] = res.getString(res.getColumnIndex(COLUMN_NOMBRE));
            data[2] = String.valueOf(res.getInt(res.getColumnIndex(COLUMN_PRECIO)));
            data[3] = res.getString(res.getColumnIndex(COLUMN_IMAGEN));

            array_list.add(data);
            res.moveToNext();
        }
        return array_list;
    }
 */



    public int numberOfRows() {
        SQLiteDatabase db = DBHelper.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE);
        return numRows;
    }


}
