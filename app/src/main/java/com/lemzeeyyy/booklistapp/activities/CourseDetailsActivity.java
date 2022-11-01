package com.lemzeeyyy.booklistapp.activities;



import static com.lemzeeyyy.booklistapp.activities.CourseListHostActivity.getCourseListItem;
import static com.lemzeeyyy.booklistapp.activities.InformationActivity.getBookItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.lemzeeyyy.booklistapp.R;
import com.lemzeeyyy.booklistapp.click_listeners.ItemListener;
import com.lemzeeyyy.booklistapp.fragments.BookDetailsFragment;
import com.lemzeeyyy.booklistapp.fragments.CourseDetailsFragment;
import com.lemzeeyyy.booklistapp.fragments.HomeFragment;
import com.lemzeeyyy.booklistapp.model.Item;

public class CourseDetailsActivity extends AppCompatActivity {
    private static Item courseItem;
    private CourseDetailsFragment courseDetailsFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bandle");
        try {
            courseItem = bundle.getParcelable("courseitemmm");
            Log.d("courseitemmm", "onCreate: "+courseItem.getVolumeInfo().getTitle());
            Toast.makeText(this,courseItem.getId(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // courseItem = bundle.getParcelable("courseitemmm");
       // courseItem = getCourseListItem();
       // Toast.makeText(this,"Welcome to course details activity", Toast.LENGTH_SHORT).show();
        if (savedInstanceState == null) {
            courseDetailsFragment = new CourseDetailsFragment();
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_course_details, CourseDetailsFragment.class, null)
                    .commit();
        }
    }




}