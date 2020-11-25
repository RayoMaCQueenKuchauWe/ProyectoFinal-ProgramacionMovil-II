package com.example.proyectofinal_programacionmovil_ii.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AssistantDB extends SQLiteOpenHelper {

    private static final String NAME_DB = "DatabaseForm.db";
    private static final int VERSION_DB = 1;
    private static final String TABLE_DB = "CREATE TABLE NewForm {idForm INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Name TEXT, Month TEXT, Year INTEGER, Active INTEGER NOT NULL}";

    public AssistantDB(@Nullable Context context) {
        super(context, NAME_DB, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DB);
        sqLiteDatabase.execSQL(TABLE_DB);
    }

    public void InsertTable(FormModel formModel) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.execSQL("INSERT INTO NewForm (Name, Month, Year, Active) VALUES('"+ formModel.getName() +"', '"+ formModel.getMonth() +"', '"+ formModel.getYear() +"', '1')");
            db.close();
        }
    }
}
