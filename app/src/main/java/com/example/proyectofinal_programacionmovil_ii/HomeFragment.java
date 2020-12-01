package com.example.proyectofinal_programacionmovil_ii;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.proyectofinal_programacionmovil_ii.models.AdapterClass;
import com.example.proyectofinal_programacionmovil_ii.models.AssistantDB;
import com.example.proyectofinal_programacionmovil_ii.models.FormModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class HomeFragment extends Fragment{

    private RecyclerView rvListForms;
    private Cursor cursor;
    private FormModel formModel;
    private ArrayList<FormModel> listForm;
    private AdapterClass adapter;
    private View view;
    private String name, month;
    private int year, idForm, aux = 0;
    int position;

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
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NewForm WHERE State = 1",null);

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

        adapter.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                idForm = listForm.get(rvListForms.getChildAdapterPosition(v)).getIdForm();
                name = listForm.get(rvListForms.getChildAdapterPosition(v)).getName();
                month = listForm.get(rvListForms.getChildAdapterPosition(v)).getMonth();
                year = listForm.get(rvListForms.getChildAdapterPosition(v)).getYear();
                return false;
            }
        });

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Intent intent = new Intent(getContext(),ActivityBill.class);
                int idForm = listForm.get(rvListForms.getChildAdapterPosition(v)).getIdForm();
                String name = listForm.get(rvListForms.getChildAdapterPosition(v)).getName();
                String month = listForm.get(rvListForms.getChildAdapterPosition(v)).getMonth();
                int year = listForm.get(rvListForms.getChildAdapterPosition(v)).getYear();
                bundle.putInt("idForm", idForm);
                bundle.putString("nameForm",name);
                bundle.putString("monthForm",month);
                bundle.putInt("yearForm",year);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        rvListForms.setAdapter(adapter);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 1001:
                callEditForm();
                break;
            case 1002:
                Toast.makeText(getContext(), "Delete", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void callEditForm() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.edit_form, null);
        final TextInputEditText etNameEdit = (TextInputEditText) view.findViewById(R.id.txtNameEdit);
        final TextInputEditText etMonthEdit = (TextInputEditText) view.findViewById(R.id.txtMonthEdit);
        final TextInputEditText etYearEdit = (TextInputEditText) view.findViewById(R.id.txtYearEdit);
        final AssistantDB assistantDB = new AssistantDB(getContext());

        Button btnEdit = (Button) view.findViewById(R.id.btnEdit);

        etNameEdit.setText(name);
        etMonthEdit.setText(month);
        etYearEdit.setText(String.valueOf(year));

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String name, month, year;
                    name = etNameEdit.getText().toString().trim();
                    month = etMonthEdit.getText().toString().trim();
                    year = etYearEdit.getText().toString().trim();
                    if (name.equals("")) {
                        etNameEdit.setError("The field is empty");
                        etNameEdit.requestFocus();
                        return;
                    }
                    if (month.equals("")) {
                        etMonthEdit.setError("The field is empty");
                        etMonthEdit.requestFocus();
                        return;
                    }
                    if (year.equals("")) {
                        etYearEdit.setError("The field is empty");
                        etYearEdit.requestFocus();
                        return;
                    }
                    if(month.equals("January") || month.equals("February") || month.equals("March") || month.equals("April") || month.equals("May") || month.equals("June") || month.equals("July") || month.equals("August") || month.equals("September") || month.equals("October") || month.equals("November") || month.equals("December")) {
                        //
                    } else {
                        etMonthEdit.setError("Incorrect format for the month. Example (January, February, etc.)");
                        etMonthEdit.requestFocus();
                        return;
                    }
                    if (Integer.parseInt(year) < 2020 || Integer.parseInt(year) > 2021) {
                        etYearEdit.setError("The year should be between 2020 and 2021");
                        etYearEdit.requestFocus();
                        return;
                    }

                    int yearEdit = Integer.parseInt(etYearEdit.getText().toString().trim());
                    formModel = new FormModel(idForm, name, month, yearEdit);
                    assistantDB.UpdateTabla(formModel);
                    Toast.makeText(getContext(), "Form updated correctly", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getContext(),MainActivity.class);
                    startActivity(intent);

                } catch (Exception ex) {
                    Toast.makeText(getContext(), "Error: " + ex, Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}