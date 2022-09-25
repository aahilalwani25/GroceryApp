package com.example.grocerydeliveryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.grocerydeliveryapp.Fruits.FruitsFragment;
import com.example.grocerydeliveryapp.Vegetables.VegetablesFragment;
import com.example.grocerydeliveryapp.admin.AdminLoginActivity;
import com.example.grocerydeliveryapp.admin.MainAdminActivity;
import com.google.android.material.navigation.NavigationView;

public class Main_Screen_Activity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        togglingNavDrawer();

        loginButton=findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main_Screen_Activity.this, AdminLoginActivity.class);
                startActivity(intent);
            }
        });

        if (savedInstanceState == null) {
            loadFragment(new FruitsFragment(this));
        }
    }

    public void loadFragment(Fragment fragment) {

        //Fragment manager manages fragments
        FragmentManager manager = getSupportFragmentManager();

        // create a FragmentTransaction to begin the transaction and add the Fragment
        FragmentTransaction transaction = manager.beginTransaction();

        //add a new fragment initially
        transaction.replace(R.id.fragments, fragment);

        //perform the transaction and save its changes
        transaction.commit();

    }

    public void togglingNavDrawer() {
        //first toggling drawer layout
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        //first activating toolbar
        setSupportActionBar(toolbar);

        //toggling drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        //menu item click listener
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                openFragmentItem(item);
                return true;
            }
        });

    }

    //opens fragment w.r.t menu item selected
    public void openFragmentItem(MenuItem item){
        Fragment fragment=null;

        switch (item.getItemId()){

            case R.id.fruits:
                fragment=new FruitsFragment(this);
                break;
            case R.id.vegetables:
                fragment=new VegetablesFragment(this);
                break;
            default:
                break;
        }

        loadFragment(fragment);
    }


}




