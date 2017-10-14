package com.cristhoper.mypersonalapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.cristhoper.mypersonalapp.R;
import com.cristhoper.mypersonalapp.fragments.HomeFragment;
import com.cristhoper.mypersonalapp.fragments.SettingsFragment;
import com.cristhoper.mypersonalapp.models.User;
import com.cristhoper.mypersonalapp.repositories.UserRepository;

public class DashboardActivity extends AppCompatActivity {

    // SharedPreferences
    private SharedPreferences sharedPreferences;

    //Drawer Layout
    DrawerLayout drawerLayout;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // init SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        //Retorna un FragmentManager para interactúar con fragmentos asociados a esta actividad;
        fragmentManager = getSupportFragmentManager();

        Fragment fragHome = new HomeFragment();
        fragmentManager.beginTransaction().replace(R.id.content, fragHome).addToBackStack("fragHome").commit();

        //Set DrawerLayout
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        // Setear Toolbar como action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); //Set a Toolbar to act as the ActionBar for this Activity window.

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, android.R.string.ok, android.R.string.cancel);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Set NavigationItemSelectedListener
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        Fragment fragHome = new HomeFragment();
                        fragmentManager.beginTransaction().replace(R.id.content, fragHome).addToBackStack("fragHome").commit();
                        break;

                    case R.id.nav_conf:
                        Fragment fragConf = new SettingsFragment();
                        fragmentManager.beginTransaction().replace(R.id.content,fragConf).addToBackStack("fragConf").commit();
                        break;

                    case R.id.nav_logout:
                        // remove from SharedPreferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        boolean success = editor.putBoolean("islogged", false).commit();
                        //        boolean success = editor.clear().commit(); // not recommended
                        startActivity(new Intent(DashboardActivity.this, MainActivity.class));
                        finish();
                        break;
                }

                //Close drawer
                drawerLayout.closeDrawer(GravityCompat.START);

                //Si retorna false el item seleccionado no se marca
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: // Option open drawer
                if(!drawerLayout.isDrawerOpen(GravityCompat.START))
                    drawerLayout.openDrawer(GravityCompat.START);   // Open drawer
                else
                    drawerLayout.closeDrawer(GravityCompat.START);    // Close drawer
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*public void callLogout(View view){
        // remove from SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean success = editor.putBoolean("islogged", false).commit();
//        boolean success = editor.clear().commit(); // not recommended

        finish();
    }*/
}

