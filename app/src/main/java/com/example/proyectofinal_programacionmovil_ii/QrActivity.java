package com.example.proyectofinal_programacionmovil_ii;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyectofinal_programacionmovil_ii.models.BillClass;
import com.google.zxing.Result;

import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QrActivity extends AppCompatActivity {

    private ZXingScannerView scannerView;
    private TextView nitQr, billQr, authorizationQr, dateQr, totalQr, codeQr;
    private Button btnScanner;
    private List<BillClass> listBill;
    BillClass billClass;

    int idForm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        Bundle bundle = getIntent().getExtras();
        idForm = bundle.getInt("idForm");

        btnScanner = findViewById(R.id.btnScanner);

        btnScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scannerView = new ZXingScannerView(getApplicationContext());
                setContentView(scannerView);
                scannerView.setResultHandler(new ZScanner());
                scannerView.startCamera();
            }
        });
    }

    class ZScanner implements ZXingScannerView.ResultHandler {

        @Override
        public void handleResult(Result result) {
            billClass = new BillClass();
            String data = result.getText();

            String nit = data.substring(0,9);
            String bill = data.substring(9,12);
            String authorization = data.substring(12,27);
            String date = data.substring(27,37);
            String total = data.substring(38,44);
            String code = data.substring(44,55);

            listBill.add(new BillClass(Integer.parseInt(nit),Integer.parseInt(bill),Integer.parseInt(authorization),Double.parseDouble(total),date,code,idForm));
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
        }
    }
}