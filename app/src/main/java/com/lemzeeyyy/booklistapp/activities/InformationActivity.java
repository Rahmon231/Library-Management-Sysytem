package com.lemzeeyyy.booklistapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.lemzeeyyy.booklistapp.R;
import com.lemzeeyyy.booklistapp.adapter.BookListAdapter;
import com.lemzeeyyy.booklistapp.click_listeners.CourseCategoryListener;
import com.lemzeeyyy.booklistapp.click_listeners.ItemListener;
import com.lemzeeyyy.booklistapp.model.Item;
import com.lemzeeyyy.booklistapp.viewmodel.BookViewModel;

import java.util.Objects;

public class InformationActivity extends AppCompatActivity implements ItemListener, CourseCategoryListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private AppBarConfiguration mAppBarConfiguration;
    private NavHostFragment navHostFragment;
    private static Item bookItem;
    private String courseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        drawerLayout = findViewById(R.id.drawer_layour_id);
        navigationView = findViewById(R.id.nav_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_fav_book, R.id.nav_find_tutor)
                .setOpenableLayout(drawerLayout)
                .build();

        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_information);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
//        Log.d("CourseNameInfo", "onCreate: "+courseName);
//        Bundle sendData = new Bundle();
//        sendData.putString("data",courseName);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_drawer_menu,menu);
        return true;
    }

    public static Item getBookItem(){
        return bookItem;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        NavController navController = navHostFragment.getNavController();
        switch (item.getItemId()){
            case R.id.homeBtnNavDrawer:
                Toast.makeText(InformationActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                Log.d("TAGMENU", "onOptionsItemSelected: "+item.getTitle());
                break;
            case R.id.menu_profile_group:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.logoutNavDrawer:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(InformationActivity.this, LoginActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                return true;
                   }
        drawerLayout.closeDrawer(GravityCompat.START);
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
       // navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_information);
        NavController navController = navHostFragment.getNavController();

        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void sendItem(Item item) {
        bookItem = item;
    }

    @Override
    public void sendCourse(String course) {
        courseName = course;
        Bundle sendData = new Bundle();
        sendData.putString("data",courseName);
    }


}