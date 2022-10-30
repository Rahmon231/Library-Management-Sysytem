package com.lemzeeyyy.booklistapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.lemzeeyyy.booklistapp.R;
import com.lemzeeyyy.booklistapp.fragments.CourseListFragment;

public class CourseListHostActivity extends AppCompatActivity  {
    private CourseListFragment courseListFragment;
    private static String selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list_host);

        Intent intent = getIntent();
        selected =intent.getStringExtra("selected_book");


        if (savedInstanceState == null) {
            courseListFragment = new CourseListFragment();
           Bundle bundle = new Bundle();
           bundle.putString("selectedcourse",selected);
           courseListFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_course_host, CourseListFragment.class, null)
                    .commit();
        }
    }

}
