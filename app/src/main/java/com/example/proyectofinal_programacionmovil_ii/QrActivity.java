package com.example.proyectofinal_programacionmovil_ii;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.proyectofinal_programacionmovil_ii.models.AdapterQR;
import com.example.proyectofinal_programacionmovil_ii.models.BillClass;
import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QrActivity extends AppCompatActivity {

    private ZXingScannerView scannerView;
    private TextView nitQr, billQr, authorizationQr, dateQr, totalQr, codeQr;
    private Button btnScanner;
    private RecyclerView rvListQR;
    private ArrayList<BillClass> listBills;
    BillClass billClass;
    private AdapterQR adapterQR;
    private String nit, bill, authorization, total, date, code;

    int idForm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        Bundle bundle = getIntent().getExtras();
        idForm = bundle.getInt("idForm");

        btnScanner = findViewById(R.id.btnScanner);
        rvListQR = findViewById(R.id.rvListQr);
        rvListQR.setLayoutManager(new GridLayoutManager(this,1));
        listBills = new ArrayList<BillClass>();

        btnScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scannerView = new ZXingScannerView(getApplicationContext());
                setContentView(scannerView);
                scannerView.setResultHandler(new ZScanner());
                scannerView.startCamera();
            }
        });
    }//FinOncreate

    class ZScanner implements ZXingScannerView.ResultHandler {

        @Override
        public void handleResult(Result result) {
            String data = result.getText();

            nit = data.substring(0,9);
            bill = data.substring(9,12);
            authorization = data.substring(12,20);
            date = data.substring(20,30);
            total = data.substring(31,34);
            code = data.substring(34,48);

            setContentView(R.layout.activity_qr);
            scannerView.stopCamera();

            nitQr = findViewById(R.id.tvNitQr);
            nitQr.setText(nit);

            billQr = findViewById(R.id.tvBillQr);
            billQr.setText(bill);

            authorizationQr = findViewById(R.id.tvAuthorizationQr);
            authorizationQr.setText(authorization);

            dateQr = findViewById(R.id.tvDateQr);
            dateQr.setText(date);

            totalQr = findViewById(R.id.tvTotalQr);
            totalQr.setText(total);

            codeQr = findViewById(R.id.tvCodeQr);
            codeQr.setText(code);

            billClass =  new BillClass(Integer.parseInt(nit),Integer.parseInt(bill),Integer.parseInt(authorization),Double.parseDouble(total),date,code,idForm);
            listBills.add(billClass);
            adapterQR =  new AdapterQR(listBills);
            rvListQR.setAdapter(adapterQR);
        }
    }
}