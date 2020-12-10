package com.example.proyectofinal_programacionmovil_ii;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.proyectofinal_programacionmovil_ii.models.BillClass;
import com.example.proyectofinal_programacionmovil_ii.models.ControllerBill;
import com.example.proyectofinal_programacionmovil_ii.models.DatePickerFragment;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;


public class FragmentAddBill extends Fragment {

    private TextInputEditText etNit, etNroBill, etAuthorization, etAmount, etCode, etDate;
    private Button btnAddBill;
    private View view;
    private BillClass billClass;
    private ControllerBill controllerBill;
    private int ID;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_bill, container, false);
        etNit = view.findViewById(R.id.txtSupplier);
        etNroBill = view.findViewById(R.id.txtNroBill);
        etAuthorization = view.findViewById(R.id.txtAuthorization);
        etAmount = view.findViewById(R.id.txtAmount);
        etCode = view.findViewById(R.id.txtCodeControl);
        etDate = view.findViewById(R.id.txtDate);
        btnAddBill = view.findViewById(R.id.btnAddBill);
        controllerBill = new ControllerBill(getContext());

        ID = getArguments().getInt("idForm");
        btnAddBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (Validate()) {
                        String NIT, NroBill, NroAuthorization, DateIssued, CodeControl, Import;

                        NIT = etNit.getText().toString().trim();
                        NroBill = etNroBill.getText().toString().trim();
                        NroAuthorization = etAuthorization.getText().toString().trim();
                        DateIssued = etDate.getText().toString().trim();
                        CodeControl = etCode.getText().toString().trim();
                        Import = etAmount.getText().toString().trim();

                        int nit = Integer.parseInt(NIT), nroBill = Integer.parseInt(NroBill), nroAuth = Integer.parseInt(NroAuthorization);
                        double importDouble = Double.parseDouble(Import);
                        billClass = new BillClass(nit, nroBill, nroAuth, importDouble, DateIssued, CodeControl, ID);

                        long insert = controllerBill.newBill(billClass);
                        if (insert == -1) {
                            Toast.makeText(getContext(),"Error: Unrecorded invoice",Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getContext(), "Invoice posted", Toast.LENGTH_LONG).show();
                            ClearForm();
                        }
                    } else {
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception ex) {
                    Toast.makeText(getContext(), "Error: " + ex, Toast.LENGTH_LONG).show();
                }
            }
        });//FinOnclick

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        return view;
    } //FinOnCreateView

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                final String selectedDate = twoDigits(day) + " / " + twoDigits(month+1) + " / " + year;
                Calendar cal = Calendar.getInstance();
                if(day > cal.get(Calendar.DAY_OF_MONTH) || month > cal.get(Calendar.MONTH) || year > cal.get(Calendar.YEAR)){
                    etDate.setError("The date can't be higher than today's");
                    etDate.requestFocus();
                } else {
                    etDate.setText(selectedDate);
                }
            }
        });

        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    private String twoDigits(int n) {
        return (n<=9) ? ("0"+n) : String.valueOf(n);
    }

    private boolean Validate () {
        boolean res = false;
        String NIT, NroBill, NroAuthorization, DateIssued, CodeControl, Import;

        NIT = etNit.getText().toString().trim();
        NroBill = etNroBill.getText().toString().trim();
        NroAuthorization = etAuthorization.getText().toString().trim();
        DateIssued = etDate.getText().toString().trim();
        CodeControl = etCode.getText().toString().trim();
        Import = etAmount.getText().toString().trim();

        if (NIT.equals("")) {
            etNit.setError("The field is required");
            etNit.requestFocus();
        } else if (NroBill.equals("")) {
            etNroBill.setError("The field is required");
            etNroBill.requestFocus();
        } else if (NroAuthorization.equals("")) {
            etAuthorization.setError("The field is required");
            etAuthorization.requestFocus();
        } else if (DateIssued.equals("")) {
            etDate.setError("The field is required");
            etDate.requestFocus();
        } else if (CodeControl.equals("")) {
            etCode.setError("The field is required");
            etCode.requestFocus();
        } else if (Import.equals("")) {
            etAmount.setError("The field is required");
            etAmount.requestFocus();
        } else {
            res = true;
        }
        return res;
    }

    private void ClearForm() {
        etNit.setText("");
        etAmount.setText("");
        etDate.setText("");
        etCode.setText("");
        etAuthorization.setText("");
        etNroBill.setText("");

    }
}