package com.lemzeeyyy.booklistapp.fragments;

import static com.lemzeeyyy.booklistapp.activities.CourseActivity.COURSE;
import static com.lemzeeyyy.booklistapp.fragments.HomeFragment.getItemAfterObserved;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lemzeeyyy.booklistapp.activities.CourseActivity;
import com.lemzeeyyy.booklistapp.activities.CourseDetailsHostActivity;
import com.lemzeeyyy.booklistapp.adapter.CourseFragmentRecyclerAdapter;
import com.lemzeeyyy.booklistapp.activities.MainActivity;
import com.lemzeeyyy.booklistapp.R;
import com.lemzeeyyy.booklistapp.adapter.BookListAdapter;
import com.lemzeeyyy.booklistapp.click_listeners.BookClickListener;
import com.lemzeeyyy.booklistapp.click_listeners.CourseClickListener;
import com.lemzeeyyy.booklistapp.click_listeners.ItemListener;
import com.lemzeeyyy.booklistapp.model.Item;
import com.lemzeeyyy.booklistapp.viewmodel.BookViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourseFragment extends Fragment implements BookClickListener,CourseClickListener {
    private Toolbar toolbar;
    private String[] courseCategory;
    private List<String> scienceCoursesList;
    private String courseName;
    private ArrayList<String> scienceCourses;
    private CourseFragmentRecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;
    private BookViewModel viewModel;
    private BookListAdapter bookListAdapter;
    private ItemListener itemListener;
    private Item courseItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_course, container, false);
        viewModel = new ViewModelProvider(this).get(BookViewModel.class);
        setUpToolBar(view);
        setCoursesOfCourseCat();
        populateRecyclerView(view);
       // observeChange();
        Log.d("tagre", "onCourseClick: "+courseItem);


        return view;

    }

    public void searchBookApi(String query){
        viewModel.searchBookApi(query);

    }

    private   void observeChange(){
        viewModel.getItems().observe(getViewLifecycleOwner(), new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {

                for (Item item : items) {
                    bookListAdapter.setBookList(items);
                    courseItem = item;
                    itemListener.sendItem(item);

                    try {

                       // Log.d("CheckingBookItem", "onChanged: "+courseItem.getVolumeInfo().getTitle());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }


    private void setCoursesOfCourseCat(){
        CourseActivity courseActivity = (CourseActivity) this.getActivity();
        courseName = courseActivity.getMyData();

        switch (courseName){
            case "science" :
                courseCategory = getActivity().getResources().getStringArray(R.array.science_courses);
                break;
            case "art" :
                courseCategory = getActivity().getResources().getStringArray(R.array.art_courses);
                break;
            case "trivia" :
                break;
            case "finance" :
                break;
        }
        scienceCoursesList = Arrays.asList(courseCategory);
        scienceCourses = listToArrayList(scienceCoursesList);

    }

    private void setUpToolBar(View view){
        CourseActivity courseActivity = (CourseActivity) this.getActivity();
        courseName = courseActivity.getMyData();
        String co = courseName.toUpperCase();
        toolbar = view.findViewById(R.id.toolbar_course);
        toolbar.setTitle(co);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> {
            //Toast.makeText(CourseFragment.this.getContext(), "Back Pressed", Toast.LENGTH_SHORT).show();
            CourseFragment.this.getActivity().finish();
        });

    }

    private void populateRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.courses_recycler);
        bookListAdapter = new BookListAdapter(this,getContext());
        recyclerAdapter = new CourseFragmentRecyclerAdapter(scienceCourses,getContext(),this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerAdapter);
    }



    private void getDataFromIntent(){
        Bundle bundle = this.getArguments();
        courseName = bundle.getString(COURSE);

        Log.d("CourseFragData", "getDataFromIntent: "+courseName);


    }
    public static ArrayList<String> listToArrayList(List<String> myList) {
        ArrayList<String> arl = new ArrayList<String>();
        for (Object object : myList) {
            arl.add((String) object);
        }
        return arl;

    }

    @Override
    public void onCourseClick(int pos) {
        String selectedBook = recyclerAdapter.getSelectedCourse(pos);
        searchBookApi(selectedBook);
        observeChange();
        Toast.makeText(getContext(), selectedBook, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), CourseDetailsHostActivity.class);
        intent.putExtra("selected_book",selectedBook);
        startActivity(intent);
    }

    @Override
    public void onBookClickListener(int position) {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.itemListener = (CourseActivity) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.itemListener = null;
    }

}
