package com.lemzeeyyy.booklistapp.fragments;

import static com.lemzeeyyy.booklistapp.CourseActivity.COURSE;
import static com.lemzeeyyy.booklistapp.InformationActivity.bookItem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lemzeeyyy.booklistapp.R;
import com.lemzeeyyy.booklistapp.model.Item;

public class CourseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course, container, false);
    }
    private void getDataFromIntent(){

        Bundle bundle = this.getArguments();
        assert bundle != null;
        String courseName = bundle.getString(COURSE);
        Log.d("CourseFragData", "getDataFromIntent: "+courseName);


    }

}