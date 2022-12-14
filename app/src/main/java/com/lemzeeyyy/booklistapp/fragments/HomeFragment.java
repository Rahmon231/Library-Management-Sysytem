package com.lemzeeyyy.booklistapp.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.lemzeeyyy.booklistapp.activities.CourseActivity;
import com.lemzeeyyy.booklistapp.activities.InformationActivity;
import com.lemzeeyyy.booklistapp.activities.MainActivity;
import com.lemzeeyyy.booklistapp.R;
import com.lemzeeyyy.booklistapp.adapter.BookListAdapter;
import com.lemzeeyyy.booklistapp.click_listeners.BookClickListener;
import com.lemzeeyyy.booklistapp.click_listeners.CourseCategoryListener;
import com.lemzeeyyy.booklistapp.click_listeners.ItemListener;
import com.lemzeeyyy.booklistapp.model.Item;
import com.lemzeeyyy.booklistapp.viewmodel.BookViewModel;

import java.util.List;


public class HomeFragment extends Fragment implements BookClickListener, View.OnClickListener{
    private BookViewModel viewModel;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private BookListAdapter bookListAdapter;
    private LinearLayout recyclerContainer;
    private ConstraintLayout coursesLayout;
    private ItemListener itemListener;
    private CourseCategoryListener courseCategoryListener;
    private RelativeLayout scienceCourse, artCourse,triviaCourse,financeCourse;
    private String courseName = "";
    private BottomNavigationView bottomNavigationView;

    Item book;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home,container,false);

        viewModel = new ViewModelProvider(this).get(BookViewModel.class);
        observeChange();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(view);
        configureRecyclerView();
        setupSearchView();
        scienceCourse.setOnClickListener(this);
        artCourse.setOnClickListener(this);
        triviaCourse.setOnClickListener(this);
        financeCourse.setOnClickListener(this);



        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homeBtnBottom:
                        Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.courseIdBottom:
                        Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();
                        FavBookFragment fragment1 = new FavBookFragment();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.nav_host_fragment_content_information, fragment1);
                        fragmentTransaction.addToBackStack("BACK");
                        fragmentTransaction.commit();
                        return true;
                    case R.id.exploreBottom:
                        Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.contactBottom:
                        Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });

    }

    private void configureRecyclerView() {
        bookListAdapter = new BookListAdapter(this,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL,
                false));
        recyclerView.setAdapter(bookListAdapter);
    }

    private void initializeViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerView_home);
        searchView = view.findViewById(R.id.search_view_home);
        recyclerContainer = view.findViewById(R.id.dept_text);
        coursesLayout = view.findViewById(R.id.courses_id_home);
        financeCourse = view.findViewById(R.id.finance_rel);
        scienceCourse = view.findViewById(R.id.science_rel);
        artCourse = view.findViewById(R.id.art_rel);
        triviaCourse = view.findViewById(R.id.trivia_rel);
        bottomNavigationView = view.findViewById(R.id.bottomNavigationView);

    }

    private void setupSearchView() {
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
        viewModel.getItems().observe(getViewLifecycleOwner(), new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                for (Item item :
                        items) {
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

    @Override
    public void onBookClickListener(int position) {

        Intent intent = new Intent(getActivity(),MainActivity.class);
        Fragment bookDetailsFragment = new BookDetailsFragment();
        Bundle bundle = new Bundle();
        book = bookListAdapter.getSelectedBook(position);
        bundle.putParcelable("books",book);
        Toast.makeText(getActivity(), book.getVolumeInfo().getTitle(), Toast.LENGTH_SHORT).show();
        itemListener.sendItem(book);
        startActivity(intent);

    }

    public interface ItemListener {
        void sendItem(Item item);
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
//       this.itemListener = (InformationActivity) context;
//       this.courseCategoryListener = (InformationActivity) context;
        if (context instanceof CourseCategoryListener){
            this.courseCategoryListener = (CourseCategoryListener) context;
        }else {
            throw new ClassCastException(context.toString()
            +"must implement ItemListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.itemListener = null;
        courseCategoryListener = null;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), CourseActivity.class);
        switch (v.getId()){
            case R.id.art_rel:
                courseName = "art";
                //Toast.makeText(getActivity(), courseName, Toast.LENGTH_SHORT).show();
                courseCategoryListener.sendCourse(courseName);
                intent.putExtra("course",courseName);
                startActivity(intent);
                break;
            case R.id.science_rel:
               // Toast.makeText(getActivity(), "Science Clicked", Toast.LENGTH_SHORT).show();
                courseName = "science";
                courseCategoryListener.sendCourse(courseName);
                intent.putExtra("course",courseName);
                startActivity(intent);
                break;
            case R.id.trivia_rel:
                //Toast.makeText(getActivity(), "Trivia Clicked", Toast.LENGTH_SHORT).show();
                courseName = "trivia";
                courseCategoryListener.sendCourse(courseName);
                intent.putExtra("course",courseName);
                startActivity(intent);

                break;
            case R.id.finance_rel:
               // Toast.makeText(getActivity(), "Finance Clicked", Toast.LENGTH_SHORT).show();
                courseName = "finance";
                courseCategoryListener.sendCourse(courseName);
                intent.putExtra("course",courseName);
                startActivity(intent);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }



}