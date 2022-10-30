package com.lemzeeyyy.booklistapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lemzeeyyy.booklistapp.R;
import com.lemzeeyyy.booklistapp.activities.CourseDetailsActivity;
import com.lemzeeyyy.booklistapp.activities.MainActivity;
import com.lemzeeyyy.booklistapp.adapter.BookListAdapter;
import com.lemzeeyyy.booklistapp.adapter.CourseListAdapter;
import com.lemzeeyyy.booklistapp.click_listeners.BookClickListener;
import com.lemzeeyyy.booklistapp.click_listeners.ItemListener;
import com.lemzeeyyy.booklistapp.model.Item;
import com.lemzeeyyy.booklistapp.viewmodel.BookViewModel;

import java.util.List;

public class CourseListFragment extends Fragment implements BookClickListener {
    private String courseName;
    private Toolbar toolbar;
    private BookViewModel viewModel;
    private CourseListAdapter courseListAdapter;
    private RecyclerView recyclerView;
    private ItemListener itemListener;
    Item course;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_course_list, container, false);

        configureRecycler(view);
        getDataFromIntent();
        setUpToolBar(view);
        Log.d("TAG", "onCreateView: "+courseName);
        viewModel = new ViewModelProvider(this).get(BookViewModel.class);
        viewModel.searchBookApi(courseName);
        observeChange();
        return view;

    }

    private void configureRecycler(View view) {
        recyclerView = view.findViewById(R.id.recyclerView_course_list);
        courseListAdapter = new CourseListAdapter(this,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL,
                false));
        recyclerView.setAdapter(courseListAdapter);
    }


    private String getDataFromIntent(){
        Intent intent = this.getActivity().getIntent();
        courseName = intent.getStringExtra("selected_book");
        return courseName;
    }
    private void setUpToolBar(View view){
        toolbar = view.findViewById(R.id.toolbar_course_list);
        toolbar.setTitle(courseName);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> {
            CourseListFragment.this.getActivity().finish();
        });
    }


    private void observeChange(){
        viewModel.getItems().observe(getViewLifecycleOwner(), new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                for (Item item :
                        items) {
                    courseListAdapter.setBookList(items);

                }
            }
        });
    }

    @Override
    public void onBookClickListener(int position) {
        Intent intent = new Intent(getActivity(), CourseDetailsActivity.class);
        course = courseListAdapter.getSelectedBook(position);
        Bundle bundle = new Bundle();
        bundle.putParcelable("courseitemmm",course);
        intent.putExtra("bandle",bundle);
        Log.d("TAGcop", "onBookClickListener: "+course.getVolumeInfo().getTitle());
       //itemListener.sendItem(course);
        startActivity(intent);
    }
}