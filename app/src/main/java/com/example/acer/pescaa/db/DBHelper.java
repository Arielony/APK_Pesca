package com.example.acer.pescaa.db;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    private static DBHelper sInstance;
    public static final String DATABASE_NAME = "Pescaa.db";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    public static synchronized DBHelper getInstance(Context context) {

        // Devuelve el estado actual del DBHelper
        if (sInstance == null) {
            sInstance = new DBHelper(context.getApplicationContext());
        }
        return sInstance;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        // CREATE DB
        db.execSQL("CREATE TABLE USUARIOS " + "(ID integer PRIMARY KEY, USERNAME text, PASSWORD text, EMAIL text, RUT text)" );
        db.execSQL("CREATE TABLE PRODUCTOS " + "(ID integer PRIMARY KEY, NOMBRE text, PRECIO integer,CANTIDAD integer, IMAGEN text)" );

        // INSERT USUARIO
        db.execSQL("INSERT INTO USUARIOS VALUES " + "(01,'nina' , 'nina' , 'nina@gmail.com',1-9 )" );


        // INSERT PRODUCTOS
        db.execSQL("INSERT INTO PRODUCTOS VALUES " + "(1 , 'Anzuelo' , 3900 ,1,'anzuelo.jpeg' )" );
        db.execSQL("INSERT INTO PRODUCTOS VALUES " + "(2 , 'Señuelo Pez' , 14000 ,1, 'pez.jpg' )" );
        db.execSQL("INSERT INTO PRODUCTOS VALUES " + "(3 , 'Señuelo Blanco' , 3900 ,1, 'carnada.jpg' )" );
        db.execSQL("INSERT INTO PRODUCTOS VALUES " + "(4 , 'Wader Pants' , 160500 ,1, 'pantalon.jpg' )" );
        db.execSQL("INSERT INTO PRODUCTOS VALUES " + "(5 , 'Lineas Pesca' , 16500 ,1, 'multi.jpg' )" );
        db.execSQL("INSERT INTO PRODUCTOS VALUES " + "(6 , 'Caña Daiwa' , 24000 ,1, 'cana.jpeg' )" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // DROP & CREATE DB
        db.execSQL("DROP TABLE IF EXISTS USUARIOS");
        db.execSQL("DROP TABLE IF EXISTS PRODUCTOS");
        onCreate(db);
    }


/*

    public boolean updateSomething(Integer id, String name, String phone, String email, String street, String place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.update("contacts", contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteById(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "id = ? ",
                new String[]{Integer.toString(id)});
    }

     */
}