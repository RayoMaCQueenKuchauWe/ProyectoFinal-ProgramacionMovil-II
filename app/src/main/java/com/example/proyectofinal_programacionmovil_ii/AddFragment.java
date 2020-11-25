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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment {

    /*------- Variable -------*/
    private View view;
    private TextInputEditText etName, etMonth, etYear;
    private Button btnSave;
    private FormModel formModel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddFragment newInstance(String param1, String param2) {
        AddFragment fragment = new AddFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

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
            res = false;
        } else if (month.equals("")) {
            etMonth.setError("The field is empty");
            etMonth.requestFocus();
            res = false;
        } else if (year.equals("")) {
            etYear.setError("The field is empty");
            etYear.requestFocus();
            res = false;
        } else {
            res = true;
        }
        return res;
    }

    private void ClearForm() {
        etName.setText("");
        etMonth.setText("");
        etYear.setText("");
    }
}