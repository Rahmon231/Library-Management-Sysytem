package com.lemzeeyyy.booklistapp.fragments;

import static com.lemzeeyyy.booklistapp.activities.CourseActivity.COURSE;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lemzeeyyy.booklistapp.activities.CourseActivity;
import com.lemzeeyyy.booklistapp.activities.CourseListHostActivity;
import com.lemzeeyyy.booklistapp.adapter.CourseFragmentRecyclerAdapter;
import com.lemzeeyyy.booklistapp.R;
import com.lemzeeyyy.booklistapp.click_listeners.CourseClickListener;
import com.lemzeeyyy.booklistapp.model.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourseFragment extends Fragment implements CourseClickListener {
    private Toolbar toolbar;
    private String[] courseCategory;
    private List<String> courseList;
    private String courseName;
    private ArrayList<String> coursesArrayList;
    private CourseFragmentRecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;
    private CourseActivity courseActivity;
    private String co;

    private Item courseItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        courseActivity = (CourseActivity) this.getActivity();
        courseName = courseActivity.getMyData();
        courseName = "science";
        Log.d("Checkco", "onCreateView: "+courseName);
        View view =  inflater.inflate(R.layout.fragment_course, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);

        setCoursesOfCourseCat(view);
        setUpToolBar(view);
        populateRecyclerView(view);

    }

    private void setCoursesOfCourseCat(View v){
//        CourseActivity courseActivity = (CourseActivity) this.getActivity();
//        courseName = courseActivity.getMyData();

        switch (courseName){
            case "science" :
                courseCategory = v.getResources().getStringArray(R.array.science_courses);
                break;
            case "art" :
                courseCategory = v.getResources().getStringArray(R.array.art_courses);
                break;
            case "trivia" :
                break;
            case "finance" :
                break;
        }
        courseList = Arrays.asList(courseCategory);
        coursesArrayList = listToArrayList(courseList);

    }

    private void setUpToolBar(View view){
//        CourseActivity courseActivity = (CourseActivity) this.getActivity();
//        courseName = courseActivity.getMyData();

        toolbar = view.findViewById(R.id.toolbar_course);
        toolbar.setTitle(courseName);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> {
            //Toast.makeText(CourseFragment.this.getContext(), "Back Pressed", Toast.LENGTH_SHORT).show();
            CourseFragment.this.getActivity().finish();
        });

    }

    private void populateRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.courses_recycler);
        recyclerAdapter = new CourseFragmentRecyclerAdapter(coursesArrayList,getContext(),this);
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
        Toast.makeText(getContext(), selectedBook, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), CourseListHostActivity.class);
        intent.putExtra("selected_book",selectedBook);
        startActivity(intent);
    }

}
