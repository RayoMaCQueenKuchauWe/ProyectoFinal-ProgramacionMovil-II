package com.example.proyectofinal_programacionmovil_ii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Variable
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    //Fragments
    private HomeFragment homeFragment;
    private AboutFragment aboutFragment;
    private AddFragment addFragment;
    //Instance the fragments
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        HomeView();
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
                AboutView();
                break;
            case R.id.nav_add:
                AddView();
                break;
            case R.id.nav_close:
                CloseView();
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
        if (fragmentManager.findFragmentByTag("home") != null) {
            getSupportFragmentManager().beginTransaction().remove(homeFragment).commit();
        } else if (fragmentManager.findFragmentByTag("about") != null) {
            getSupportFragmentManager().beginTransaction().remove(aboutFragment).commit();
        } else if (fragmentManager.findFragmentByTag("add") != null) {
            getSupportFragmentManager().beginTransaction().remove(addFragment).commit();
        } else {
            Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show();
        }
    }

    private void HomeView() {
        try {
            DeleteFragment();
            fragmentManager = getSupportFragmentManager();
            homeFragment = new HomeFragment();
            homeFragment.setArguments(getIntent().getExtras());
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.idPanel, homeFragment, "home");
            fragmentTransaction.commit();
            navigationView.setCheckedItem(R.id.nav_home);
        } catch (Exception ex) {
            Toast.makeText(this, "Error: " + ex, Toast.LENGTH_SHORT).show();
        }
    }

    private void AboutView() {
        try {
            DeleteFragment();
            fragmentManager = getSupportFragmentManager();
            aboutFragment = new AboutFragment();
            aboutFragment.setArguments(getIntent().getExtras());
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.idPanel, aboutFragment, "about");
            fragmentTransaction.commit();
            navigationView.setCheckedItem(R.id.nav_about);
        } catch (Exception ex) {
            Toast.makeText(this, "Error: " + ex, Toast.LENGTH_SHORT).show();
        }
    }

    private void AddView() {
        try {
            DeleteFragment();
            fragmentManager = getSupportFragmentManager();
            addFragment = new AddFragment();
            addFragment.setArguments(getIntent().getExtras());
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.idPanel, addFragment, "add");
            fragmentTransaction.commit();
            navigationView.setCheckedItem(R.id.nav_add);
        } catch (Exception ex) {
            Toast.makeText(this, "Error: " + ex, Toast.LENGTH_SHORT).show();
        }
    }

    private void CloseView() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Do you want to leave the application?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog title = alert.create();
        title.setTitle("Close");
        title.show();
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
