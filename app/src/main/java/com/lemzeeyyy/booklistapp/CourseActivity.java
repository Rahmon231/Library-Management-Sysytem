package com.lemzeeyyy.booklistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.lemzeeyyy.booklistapp.click_listeners.CourseListener;
import com.lemzeeyyy.booklistapp.fragments.BookDetailsFragment;
import com.lemzeeyyy.booklistapp.fragments.CourseFragment;

public class CourseActivity extends AppCompatActivity {
    public static final String COURSE = "courseFromCourseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        if(savedInstanceState == null){
            CourseFragment courseFragment = new CourseFragment();
            Bundle bundle = new Bundle();
            Intent intent = getIntent();
            String course = intent.getStringExtra("course");

            switch (course){
                case "science":
                    bundle.putString(COURSE,"science");
                    courseFragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .add(R.id.course_fragment_container, CourseFragment.class, null)
                            .commit();
                    break;
                case "art":
                    bundle.putString(COURSE,"art");
                    courseFragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .add(R.id.course_fragment_container, CourseFragment.class, null)
                            .commit();
                    break;
                case "trivia":
                    bundle.putString(COURSE,"trivia");
                    courseFragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .add(R.id.course_fragment_container, CourseFragment.class, null)
                            .commit();
                    break;
                case "finance":
                    bundle.putString(COURSE,"finance");
                    courseFragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .add(R.id.course_fragment_container, CourseFragment.class, null)
                            .commit();
                    break;
                default:
                    intent.putExtra(COURSE,"null");
            }
        }
    }
}