package com.example.proyectofinal_programacionmovil_ii;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectofinal_programacionmovil_ii.models.AssistantDB;
import com.example.proyectofinal_programacionmovil_ii.models.FormModel;
import com.google.android.material.textfield.TextInputEditText;

public class AddFragment extends Fragment {

    /*------- Variable -------*/
    private View view;
    private TextInputEditText etName, etMonth, etYear;
    private Button btnSave;
    private FormModel formModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add, container, false);

        /*------- Code -------*/
        etName = view.findViewById(R.id.txtName);
        etMonth = view.findViewById(R.id.txtMonth);
        etYear = view.findViewById(R.id.txtYear);
        btnSave = view.findViewById(R.id.btnSave);
        final AssistantDB assistantDB = new AssistantDB(getContext());
        
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (Validate()) {
                        String name, month;
                        int year;
                        name = etName.getText().toString().trim();
                        month = etMonth.getText().toString().trim();
                        year = Integer.parseInt(etYear.getText().toString().trim());
                        formModel = new FormModel(name, month, year);
                        assistantDB.InsertTable(formModel);
                        Toast.makeText(getContext(), "Created with success", Toast.LENGTH_SHORT).show();
                        ClearForm();
                    } else {
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex) {
                    Toast.makeText(getContext(), "Error: " + ex, Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private boolean Validate () {
        boolean res = false;
        String name, month, year;
        name = etName.getText().toString().trim();
        month = etMonth.getText().toString().trim();
        year = etYear.getText().toString().trim();
        if (name.equals("")) {
            etName.setError("The field is empty");
            etName.requestFocus();
        } else if (month.equals("")) {
            etMonth.setError("The field is empty");
            etMonth.requestFocus();
        } else if (year.equals("")) {
            etYear.setError("The field is empty");
            etYear.requestFocus();
        } else if(month.equals("January") || month.equals("February") || month.equals("March") || month.equals("April") || month.equals("May") || month.equals("June") || month.equals("July") || month.equals("August") || month.equals("September") || month.equals("October") || month.equals("November") || month.equals("December")) {
            if (Integer.parseInt(year) < 2020 || Integer.parseInt(year) > 2021) {
                etYear.setError("The year should be between 2020 and 2021");
                etYear.requestFocus();
            } else {
                res = true;
            }
        } else {
            etMonth.setError("Incorrect format for the month. Example (January, February, etc.)");
            etMonth.requestFocus();
        }
        return res;
    }

    private void ClearForm() {
        etName.setText("");
        etMonth.setText("");
        etYear.setText("");
    }
}