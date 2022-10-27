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
    Bundle bundle = new Bundle();
    String deptName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        if(savedInstanceState == null){
            CourseFragment courseFragment = new CourseFragment();
            Intent intent = getIntent();
            String course = intent.getStringExtra("course");

            switch (course){
                case "science":
                    deptName = "science";
                    bundle.putString(COURSE,deptName);
                    getMyData();
                    //Log.d("deptNameBun", "onCreate: "+bundle.getString(COURSE));
                    courseFragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .add(R.id.course_fragment_container, CourseFragment.class, null)
                            .commit();
                    break;

                case "art":
                    deptName = "art";
                    bundle.putString(COURSE,deptName);
                    courseFragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .add(R.id.course_fragment_container, CourseFragment.class, null)
                            .commit();
                    break;
                case "trivia":
                    deptName = "trivia";
                    bundle.putString(COURSE,deptName);
                    courseFragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .add(R.id.course_fragment_container, CourseFragment.class, null)
                            .commit();
                    break;
                case "finance":
                    deptName = "finance";
                    bundle.putString(COURSE,deptName);
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
    public String getMyData() {
        Log.d("CheckMyData", "getMyData: "+bundle.getString(COURSE));
        return bundle.getString(COURSE);

    }
}