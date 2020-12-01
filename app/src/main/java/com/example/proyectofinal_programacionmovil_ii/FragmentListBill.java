package com.example.proyectofinal_programacionmovil_ii;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinal_programacionmovil_ii.models.AdapterClass;
import com.example.proyectofinal_programacionmovil_ii.models.AdapterClassBill;
import com.example.proyectofinal_programacionmovil_ii.models.AssistantDB;
import com.example.proyectofinal_programacionmovil_ii.models.BillClass;
import com.example.proyectofinal_programacionmovil_ii.models.FormModel;

import java.util.ArrayList;

public class FragmentListBill extends Fragment {


    private TextView tvTitle;
    private RecyclerView rvListBills;
    private BillClass billClass;
    private ArrayList<BillClass> listBills;
    private AdapterClassBill adapter;
    private View view;
    private int ID;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_bill, container, false);
        tvTitle = view.findViewById(R.id.tvTitle);
        String nameForm = getArguments().getString("nameForm");
        String monthForm = getArguments().getString("monthForm");
        int year = getArguments().getInt("yearForm");
        ID = getArguments().getInt("idForm");
        tvTitle.setText(nameForm + ", " + monthForm + "/" + year);

        rvListBills = view.findViewById(R.id.rvListBill);
        rvListBills.setLayoutManager(new GridLayoutManager(getContext(),1));
        loadRecycler();

        return view;
    }//FinOnCreateView

    private void loadListBDD() {
        listBills.clear();

        AssistantDB assistantDB = new AssistantDB(getContext());
        SQLiteDatabase sqLiteDatabase= assistantDB.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT idBill, NroBill, DateIssued, Import FROM NewBill WHERE State = 1 AND IDForm ="+ ID,null);

        if(cursor.moveToNext()){
            do{
                BillClass auxiliary = new BillClass(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getInt(3)
                );
                listBills.add(auxiliary);
            }while(cursor.moveToNext());
        }
    }

    private void loadRecycler(){
        listBills = new ArrayList<BillClass>();
        loadListBDD();
        adapter =  new AdapterClassBill(listBills);
        rvListBills.setAdapter(adapter);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        super.onContextItemSelected(item);
        switch (item.getItemId()) {
            case 101:
                Toast.makeText(getContext(), "Edit", Toast.LENGTH_SHORT).show();
                return true;
            case 102:
                Toast.makeText(getContext(), "Delete", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }
}