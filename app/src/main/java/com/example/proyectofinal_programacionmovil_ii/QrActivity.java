package com.example.proyectofinal_programacionmovil_ii;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QrActivity extends AppCompatActivity {

    private ZXingScannerView scannerView;
    private TextView nitQr, billQr, authorizationQr, dateQr, totalQr, codeQr;
    private Button btnScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

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
            String data = result.getText();

            String nit = data.substring(0,9);
            String bill = data.substring(9,12);
            String authorization = data.substring(12,27);
            String date = data.substring(27,37);
            String total = data.substring(38,44);
            String code = data.substring(44,55);

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