package com.lemzeeyyy.booklistapp.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lemzeeyyy.booklistapp.R;
import com.lemzeeyyy.booklistapp.activities.CourseActivity;
import com.lemzeeyyy.booklistapp.activities.CourseDetailsHostActivity;
import com.lemzeeyyy.booklistapp.click_listeners.ItemListener;

public class CourseDetailsFragment extends Fragment {
    private ItemListener itemListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course_details, container, false);
    }




    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.itemListener = (CourseDetailsHostActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.itemListener = null;
    }
}