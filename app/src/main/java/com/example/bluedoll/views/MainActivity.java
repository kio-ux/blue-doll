package com.example.bluedoll.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.bluedoll.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String userID = getIntent().getStringExtra("userID");
        String userName = getIntent().getStringExtra("userName");
        String userRole = getIntent().getStringExtra("userRole");



        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Bundle bundle = new Bundle();
        bundle.putString("userName", userName);
        bundle.putString("userRole", userRole);
        Fragment viewDollsFragment = new ViewDollsFragment();
        viewDollsFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, viewDollsFragment).commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.viewDolls:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, viewDollsFragment).commit();
                        break;
                    case R.id.insertDolls:
                        Intent insertDolls = new Intent(getApplicationContext(), InsertDollsActivity.class);
                        insertDolls.putExtra("userID",userID);
                        insertDolls.putExtra("userName",userName);
                        insertDolls.putExtra("userRole",userRole);
                        startActivity(insertDolls);
                        break;
                    case R.id.menu_logout:
                        Intent logout = new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(logout);
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()== R.id.our_Location){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AboutUsFragment()).commit();
        }
        return super.onOptionsItemSelected(item);
    }

}