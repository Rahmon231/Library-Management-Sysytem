package com.lemzeeyyy.booklistapp.activities;

import static com.lemzeeyyy.booklistapp.activities.InformationActivity.bookItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.lemzeeyyy.booklistapp.R;
import com.lemzeeyyy.booklistapp.activities.InformationActivity;
import com.lemzeeyyy.booklistapp.adapter.BookListAdapter;
import com.lemzeeyyy.booklistapp.fragments.BookDetailsFragment;
import com.lemzeeyyy.booklistapp.viewmodel.BookViewModel;

public class MainActivity extends AppCompatActivity {

    BookDetailsFragment bookDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
             bookDetailsFragment = new BookDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("books",bookItem);
            bookDetailsFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_id, BookDetailsFragment.class, null)
                    .commit();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        getSupportFragmentManager().beginTransaction().remove(bookDetailsFragment).commitNowAllowingStateLoss();
    }
}
