package com.example.proyectofinal_programacionmovil_ii;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectofinal_programacionmovil_ii.models.AdapterClass;
import com.example.proyectofinal_programacionmovil_ii.models.AssistantDB;
import com.example.proyectofinal_programacionmovil_ii.models.FormModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView rvListForms;
    FormModel formModel;
    ArrayList<FormModel> listForm;
    AdapterClass adapter;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,container,false);
        rvListForms = view.findViewById(R.id.rvList);
        rvListForms.setLayoutManager(new GridLayoutManager(getContext(),1));
        loadRecycler();
        return view;
    }
    private void loadListBDD() {
        listForm.clear();

        AssistantDB assistantDB = new AssistantDB(getContext());
        SQLiteDatabase sqLiteDatabase= assistantDB.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NewForm",null);

        if(cursor.moveToNext()){
            do{
                FormModel auxiliary = new FormModel(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3)
                );
                listForm.add(auxiliary);
            }while(cursor.moveToNext());
        }
    }
    private void loadRecycler(){
        listForm = new ArrayList<FormModel>();
        loadListBDD();
        adapter =  new AdapterClass(listForm);
        rvListForms.setAdapter(adapter);
    }
}