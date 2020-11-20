package com.example.proyectofinal_programacionmovil_ii;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AyudanteBaseDatos extends SQLiteOpenHelper {

    private static final String NOMBRE_BDD = "proyectofinal.db", TABLA_FORMULARIO = "formulario";
    private static final int VERSION = 1;

    public static String getNombreBdd() {
        return NOMBRE_BDD;
    }

    public static String getTablaFormulario() {
        return TABLA_FORMULARIO;
    }

    public static int getVERSION() {
        return VERSION;
    }

    public AyudanteBaseDatos(@Nullable Context context) {
        super(context, NOMBRE_BDD,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS formulario(id INTEGER PRIMARY KEY AUTOINCREMENT, mes TEXT,año INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
