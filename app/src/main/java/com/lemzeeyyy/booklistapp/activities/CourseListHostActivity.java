package com.lemzeeyyy.booklistapp.activities;

import static com.lemzeeyyy.booklistapp.activities.InformationActivity.getBookItem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.lemzeeyyy.booklistapp.R;
import com.lemzeeyyy.booklistapp.click_listeners.ItemListener;
import com.lemzeeyyy.booklistapp.fragments.CourseListFragment;
import com.lemzeeyyy.booklistapp.model.Item;

public class CourseListHostActivity extends AppCompatActivity  implements ItemListener {
    private CourseListFragment courseListFragment;
    private static String selected;
    private static Item bookItem;

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

    public static Item getCourseListItem(){
        return bookItem;
    }


    @Override
    public void sendItem(Item item) {
        bookItem = item;

    }
}
