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
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class ActivityBill extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Variable
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
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
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        /*------------ Tool Bar ------------*/
        setSupportActionBar(toolbar);

        /*------------ Navigation Drawer Menu ------------*/
        //Hide or show items
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_profile).setVisible(false);
        menu.findItem(R.id.nav_logout).setVisible(false);
        //Setting
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        ListBill(fragmentListBill);
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_home:
                HomeView();
                break;
            case R.id.nav_about:
                Bundle bundleArgs = new Bundle();
                bundleArgs.putInt("idForm",idForm);
                FragmentAddBill fragmentAddBill = new FragmentAddBill();
                fragmentAddBill.setArguments(bundleArgs);

                AddBill(fragmentAddBill);
                break;
            case R.id.nav_add:
                ListBill(fragmentListBill);
                break;
            case R.id.nav_close:
                //CloseView();
                Toast.makeText(this, "Close", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_login:
                //Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                //startActivity(intent);
                Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void DeleteFragment() {
        fragmentManager = getSupportFragmentManager();
         if (fragmentManager.findFragmentByTag("bill") != null) {
             getSupportFragmentManager().beginTransaction().remove(fragmentAddBill).commit();
         } else if(fragmentManager.findFragmentByTag("list") != null) {
             getSupportFragmentManager().beginTransaction().remove(fragmentListBill).commit();
         } else {
            Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show();
        }
    }

    private void HomeView() {
        try {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
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
            navigationView.setCheckedItem(R.id.nav_about);
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
            navigationView.setCheckedItem(R.id.nav_add);
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