package com.example.proyectofinal_programacionmovil_ii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class ActivityBill extends AppCompatActivity {

    //Variable
    private Toolbar toolbar;
    //Fragments
    private FragmentAddBill fragmentAddBill;
    private FragmentListBill fragmentListBill;
    //Instance the fragments
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    int yearForm, idForm;
    String nameForm, monthForm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        Bundle bundle = getIntent().getExtras();
        idForm = bundle.getInt("idForm");
        yearForm = bundle.getInt("yearForm");
        nameForm = bundle.getString("nameForm");
        monthForm = bundle.getString("monthForm");

        Bundle bundleArgs = new Bundle();
        bundleArgs.putInt("idForm",idForm);
        bundleArgs.putString("nameForm",nameForm);
        bundleArgs.putString("monthForm",monthForm);
        bundleArgs.putInt("yearForm",yearForm);

        FragmentListBill fragmentListBill = new FragmentListBill();
        fragmentListBill.setArguments(bundleArgs);

        /*------------ Hooks ------------*/
        toolbar = findViewById(R.id.toolbar);

        /*------------ Tool Bar ------------*/
        setSupportActionBar(toolbar);

        ListBill(fragmentListBill);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bill, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.bill_home:
                HomeView();
                break;
            case R.id.bill_add:
                Bundle bundleArgs = new Bundle();
                bundleArgs.putInt("idForm", idForm);
                FragmentAddBill fragmentAddBill = new FragmentAddBill();
                fragmentAddBill.setArguments(bundleArgs);

                AddBill(fragmentAddBill);
                break;
            case R.id.bill_list:
                ListBill(fragmentListBill);
                break;
            case R.id.bill_qr:
                AddBillQR();
                break;
            case  R.id.bill_export:
                Toast.makeText(this, "Export to Excel", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    private void AddBillQR() {
        try {
            Bundle extras = new Bundle();
            extras.putInt("idForm", idForm);

            Intent intent = new Intent(getApplicationContext(), QrActivity.class);
            intent.putExtras(extras);
            startActivity(intent);
            DeleteFragment();
        } catch (Exception ex) {
            Toast.makeText(this, "Error: " + ex, Toast.LENGTH_SHORT).show();
        }
    }

    private void DeleteFragment() {
        fragmentManager = getSupportFragmentManager();
         if (fragmentManager.findFragmentByTag("bill") != null) {
             getSupportFragmentManager().beginTransaction().remove(fragmentAddBill).commit();
         }
         if(fragmentManager.findFragmentByTag("list") != null) {
             getSupportFragmentManager().beginTransaction().remove(fragmentListBill).commit();
         }
    }

    private void HomeView() {
        try {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            DeleteFragment();
        } catch (Exception ex) {
            Toast.makeText(this, "Error: " + ex, Toast.LENGTH_SHORT).show();
        }
    }

    private void AddBill(FragmentAddBill fragmentAddBillArgs) {
        try {
            DeleteFragment();
            fragmentManager = getSupportFragmentManager();
            fragmentAddBill = new FragmentAddBill();
            fragmentAddBill.setArguments(getIntent().getExtras());
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.idPanel, fragmentAddBill, "bill");
            fragmentTransaction.replace(R.id.FragmentAddBill, fragmentAddBillArgs);
            fragmentTransaction.commit();
        } catch (Exception ex) {
            Toast.makeText(this, "Error: " + ex, Toast.LENGTH_SHORT).show();
        }
    }

    private void ListBill(FragmentListBill fragmentListBillArgs) {
        try {
            DeleteFragment();
            fragmentManager = getSupportFragmentManager();
            fragmentListBill = new FragmentListBill();
            fragmentListBill.setArguments(getIntent().getExtras());
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.idPanel, fragmentListBill, "list");
            fragmentTransaction.replace(R.id.FragmentList, fragmentListBillArgs);
            fragmentTransaction.commit();
        } catch (Exception ex) {
            Toast.makeText(this, "Error: " + ex, Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("You are sure to get out of the system?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();
        }
        return super.onKeyDown(keyCode, event);
    }
}