package com.lemzeeyyy.booklistapp.fragments;

import static com.lemzeeyyy.booklistapp.CourseActivity.COURSE;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lemzeeyyy.booklistapp.CourseActivity;
import com.lemzeeyyy.booklistapp.CourseFragmentRecyclerAdapter;
import com.lemzeeyyy.booklistapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourseFragment extends Fragment {
    private Toolbar toolbar;
    private String[] courseCategory;
    private List<String> scienceCoursesList;
    private String courseName;
    private ArrayList<String> scienceCourses;
    private CourseFragmentRecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_course, container, false);
        toolbar = view.findViewById(R.id.toolbar_course);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);



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

        //courseCategory = getActivity().getResources().getStringArray(R.array.science_courses);
        scienceCoursesList = Arrays.asList(courseCategory);
        scienceCourses = listToArrayList(scienceCoursesList);
        populateRecyclerView(view);

        toolbar.setNavigationOnClickListener(v ->
                Toast.makeText(getContext(), "Back Pressed", Toast.LENGTH_SHORT).show());



        return view;

    }

    private void populateRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.courses_recycler);
        recyclerAdapter = new CourseFragmentRecyclerAdapter(scienceCourses,getContext());
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
}
