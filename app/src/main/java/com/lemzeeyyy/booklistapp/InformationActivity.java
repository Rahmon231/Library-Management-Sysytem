package com.lemzeeyyy.booklistapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;
import android.util.Log;
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
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        drawerLayout = findViewById(R.id.drawer_layour_id);
        navigationView = findViewById(R.id.nav_view);
        menu_icon = findViewById(R.id.menu_icon);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        menu_icon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawerLayout.openDrawer(GravityCompat.START);
//                ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(InformationActivity.this,
//                        drawerLayout,
//                        R.string.navigation_drawer_open,
//                        R.string.navigation_drawer_close);
//                drawerLayout.addDrawerListener(toggle);
//                toggle.syncState();
//
//            }
//        });
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_fav_book, R.id.nav_find_tutor)
                .setOpenableLayout(drawerLayout)
                .build();
        NavController navController = Navigation.findNavController(InformationActivity.this,
                R.id.nav_host_fragment_content_information);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_drawer_menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.homeBtnNavDrawer:
                Toast.makeText(InformationActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                Log.d("TAGMENU", "onOptionsItemSelected: "+item.getTitle());
                break;
            case R.id.menu_profile_group:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
                   }
        drawerLayout.closeDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }

}