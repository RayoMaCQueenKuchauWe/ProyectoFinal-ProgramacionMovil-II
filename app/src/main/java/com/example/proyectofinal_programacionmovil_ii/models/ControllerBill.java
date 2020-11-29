package com.example.proyectofinal_programacionmovil_ii.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ControllerBill {
    private AssistantDB assistantDB;

    public ControllerBill(Context context) {
        assistantDB=new AssistantDB(context);
    }

    public long newBill(BillClass bill){
        SQLiteDatabase sqLiteDatabase = assistantDB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NIT", bill.getNIT());
        contentValues.put("NroBill", bill.getNroBill());
        contentValues.put("NroAuthorization", bill.getNroAuthorization());
        contentValues.put("Import", bill.getImport());
        contentValues.put("DateIssued", bill.getDateIssued());
        contentValues.put("CodeControl", bill.getCodeControl());
        contentValues.put("IDForm", bill.getIdForm());

        return sqLiteDatabase.insert(assistantDB.getTableBill(),null,contentValues);
    }
}
