package com.lemzeeyyy.booklistapp.fragments;

import static com.lemzeeyyy.booklistapp.activities.CourseActivity.COURSE;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lemzeeyyy.booklistapp.R;
import com.lemzeeyyy.booklistapp.activities.CourseActivity;
import com.lemzeeyyy.booklistapp.activities.CourseDetailsHostActivity;
import com.lemzeeyyy.booklistapp.adapter.BookListAdapter;
import com.lemzeeyyy.booklistapp.click_listeners.BookClickListener;
import com.lemzeeyyy.booklistapp.click_listeners.ItemListener;
import com.lemzeeyyy.booklistapp.model.Item;
import com.lemzeeyyy.booklistapp.viewmodel.BookViewModel;

import java.util.List;

public class CourseDetailsFragment extends Fragment implements BookClickListener {
    private ItemListener itemListener;
    private String courseName;
    private BookViewModel viewModel;
    private BookListAdapter bookListAdapter;
    private Item courseItem;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_course_details, container, false);
        getDataFromIntent();
        searchBookApi(courseName);
        viewModel.getItems();
        observeChange();
        Log.d("tagitem", "onCreateView: "+courseItem);
        return view;

    }

    private void getDataFromIntent(){
        Intent intent = this.getActivity().getIntent();
        courseName = intent.getStringExtra("selected_book");

        Log.d("CourseFragCourse", "getDataFromIntent: "+courseName);

    }

    public void searchBookApi(String query){
        viewModel.searchBookApi(query);

    }

    private   void observeChange(){
        bookListAdapter = new BookListAdapter(this,getContext());
        viewModel.getItems().observe(getViewLifecycleOwner(), new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {

                for (Item item : items) {
                    bookListAdapter.setBookList(items);
                   courseItem = item;
                    try {

                        // Log.d("CheckingBookItem", "onChanged: "+courseItem.getVolumeInfo().getTitle());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });
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

    @Override
    public void onBookClickListener(int position) {

    }
}