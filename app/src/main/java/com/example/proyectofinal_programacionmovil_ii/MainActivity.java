package com.example.proyectofinal_programacionmovil_ii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
        //State the navigation
        navigationView.setCheckedItem(R.id.nav_home);
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
                break;
            case R.id.nav_about:
                Toast.makeText(this,"About", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_add:
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
                break;
            case R.id.nav_close:
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
}
