package com.example.grocerydeliveryapp.admin;

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
import android.widget.Toast;

import com.example.grocerydeliveryapp.Fruits.FruitsFragment;
import com.example.grocerydeliveryapp.Main_Screen_Activity;
import com.example.grocerydeliveryapp.R;
import com.example.grocerydeliveryapp.Vegetables.VegetablesFragment;
import com.facebook.stetho.Stetho;
import com.google.android.material.navigation.NavigationView;

public class MainAdminActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Button logoutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        Stetho.initializeWithDefaults(this);

        togglingNavDrawer();
        if (savedInstanceState == null) {

            loadFragment(new AddItemsFragment(this));
        }

        logoutButton=findViewById(R.id.logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainAdminActivity.this, "Log out successfully",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainAdminActivity.this, Main_Screen_Activity.class);
                startActivity(intent);
            }
        });
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

            case R.id.add_items_admin:
                fragment=new AddItemsFragment(this);
                break;
//            case R.id.delete_items_admin:
//                fragment=new DeleteItemFragment(this);
//                break;
            default:
                break;
        }

        loadFragment(fragment);
    }
}