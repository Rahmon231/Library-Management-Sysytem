package com.lemzeeyyy.booklistapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.lemzeeyyy.booklistapp.adapter.BookListAdapter;
import com.lemzeeyyy.booklistapp.click_listeners.BookClickListener;
import com.lemzeeyyy.booklistapp.fragments.BookListFragment;
import com.lemzeeyyy.booklistapp.fragments.HomeFragment;
import com.lemzeeyyy.booklistapp.model.Item;
import com.lemzeeyyy.booklistapp.viewmodel.BookViewModel;

import java.util.List;
import java.util.Objects;

public class InformationActivity extends AppCompatActivity implements BookClickListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private AppBarConfiguration mAppBarConfiguration;
    private FirebaseAuth auth;
    private BookViewModel viewModel;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private BookListAdapter bookListAdapter;
    private LinearLayout recyclerContainer;
    private ConstraintLayout coursesLayout;
    private NavHostFragment navHostFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        drawerLayout = findViewById(R.id.drawer_layour_id);
        navigationView = findViewById(R.id.nav_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

//        initializeView();
//        configureRecyclerView();
//        setupSearchView();
//        viewModel = new ViewModelProvider(this).get(BookViewModel.class);
//        observeChange();



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
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_information);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    /*private void setupSearchView() {
        searchView.setOnSearchClickListener(v -> {
            recyclerView.setVisibility(View.VISIBLE);
            viewModel.getItems();
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                bookListAdapter.setBookList(null);
                recyclerContainer.setVisibility(View.VISIBLE);
                coursesLayout.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                return false;
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                recyclerContainer.setVisibility(View.GONE);
                coursesLayout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                viewModel.searchBookApi(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
    public void searchBookApi(String query){
        viewModel.searchBookApi(query);

    }

    private void observeChange(){
        viewModel.getItems().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                for (Item item :
                        items) {
                    Log.d("Check Item", "onChanged: "+item.getVolumeInfo().getTitle());
                    bookListAdapter.setBookList(items);
                    try {
//                        Log.d("CheckItems", "onChanged: "+item.getVolumeInfo().getImageLinks().getSmallThumbnail()+".jpg");
//                        Log.d("CheckItems", "onChanged: "+item.getVolumeInfo().getTitle());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }


    private void initializeView() {
        recyclerView = findViewById(R.id.recyclerView_home);
        searchView = findViewById(R.id.search_view_home);
        recyclerContainer = findViewById(R.id.recyclerView_container);
        coursesLayout = findViewById(R.id.courses_id_home);
    }
    private void configureRecyclerView() {
        bookListAdapter = new BookListAdapter(this,InformationActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,
                false));
        recyclerView.setAdapter(bookListAdapter);
    }
*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_drawer_menu,menu);
        return true;
    }


//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.homeBtnNavDrawer:
//                Toast.makeText(InformationActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
//                Log.d("TAGMENU", "onOptionsItemSelected: "+item.getTitle());
//                break;
//            case R.id.menu_profile_group:
//                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.logoutNavDrawer:
//                FirebaseAuth.getInstance().signOut();
//                startActivity(new Intent(InformationActivity.this,LoginActivity.class)
//                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
//                return true;
//                   }
//        drawerLayout.closeDrawer(GravityCompat.START);
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = navHostFragment.getNavController();

        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBookClickListener(int position) {

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        NavController navController = navHostFragment.getNavController();
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item);
    }
}