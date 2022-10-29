package com.lemzeeyyy.booklistapp.activities;

import static com.lemzeeyyy.booklistapp.activities.InformationActivity.bookItem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.lemzeeyyy.booklistapp.R;
import com.lemzeeyyy.booklistapp.click_listeners.ItemListener;
import com.lemzeeyyy.booklistapp.fragments.BookDetailsFragment;
import com.lemzeeyyy.booklistapp.fragments.CourseDetailsFragment;
import com.lemzeeyyy.booklistapp.model.Item;

public class CourseDetailsHostActivity extends AppCompatActivity implements ItemListener {
    CourseDetailsFragment courseDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details_host);

        Intent intent = getIntent();
        String selected =intent.getStringExtra("selected_book");

        if (savedInstanceState == null) {
            courseDetailsFragment = new CourseDetailsFragment();
           Bundle bundle = new Bundle();
           bundle.putString("selectedcourse",selected);
           courseDetailsFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_course_host, CourseDetailsFragment.class, null)
                    .commit();
        }
    }

    @Override
    public void sendItem(Item item) {

    }
}
