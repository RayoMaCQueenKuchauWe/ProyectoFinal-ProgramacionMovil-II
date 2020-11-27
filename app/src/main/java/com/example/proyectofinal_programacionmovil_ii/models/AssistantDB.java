package com.example.proyectofinal_programacionmovil_ii.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AssistantDB extends SQLiteOpenHelper {

    private static final String NAME_DB = "DatabaseForm.db",  TABLE_DB = "NewForm";;
    private static final int VERSION_DB = 1;

    public static String getNameDb() {
        return NAME_DB;
    }

    public static String getTableDb() {
        return TABLE_DB;
    }

    public static int getVersionDb() {
        return VERSION_DB;
    }

    public AssistantDB(@Nullable Context context) {
        super(context, NAME_DB,null,VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS NewForm(idForm INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT NOT NULL,Month TEXT NOT NULL, Year INTEGER NOT NULL, State DEFAULT 1);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void InsertTable(FormModel formModel) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.execSQL("INSERT INTO NewForm (Name, Month, Year) VALUES('"+ formModel.getName() +"', '"+ formModel.getMonth() +"', '"+ formModel.getYear() +"')");
            db.close();
        }
    }
}
