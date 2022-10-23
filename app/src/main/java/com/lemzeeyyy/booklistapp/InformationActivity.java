package com.lemzeeyyy.booklistapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class InformationActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageView menu_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        drawerLayout = findViewById(R.id.drawer_layour_id);
        navigationView = findViewById(R.id.nav_view_id);
        menu_icon = findViewById(R.id.menu_icon);
        menu_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
                ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(InformationActivity.this,
                        drawerLayout,
                        R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close);
                drawerLayout.addDrawerListener(toggle);
                toggle.syncState();
                  navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
@Override
public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
        case R.id.homeBtnNavDrawer:
        Toast.makeText(InformationActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
         break;
        case R.id.faveBookBtnNavDrawer:
        Toast.makeText(InformationActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
        break;
        case R.id.findTutBtnNavDrawer:
        Toast.makeText(InformationActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
        break;
        case R.id.loginNavDrawer:
        Toast.makeText(InformationActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
        break;
        case R.id.profilePicNavDrawer:
        Toast.makeText(InformationActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
        break;
        case R.id.logoutNavDrawer:
        Toast.makeText(InformationActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
        break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
        }
        });

            }
        });

    }


}