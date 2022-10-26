package com.lemzeeyyy.booklistapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lemzeeyyy.booklistapp.InformationActivity;
import com.lemzeeyyy.booklistapp.MainActivity;
import com.lemzeeyyy.booklistapp.R;
import com.lemzeeyyy.booklistapp.adapter.BookListAdapter;
import com.lemzeeyyy.booklistapp.click_listeners.BookClickListener;
import com.lemzeeyyy.booklistapp.model.Item;
import com.lemzeeyyy.booklistapp.viewmodel.BookViewModel;

import java.util.List;


public class HomeFragment extends Fragment implements BookClickListener {
    private BookViewModel viewModel;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private BookListAdapter bookListAdapter;
    private LinearLayout recyclerContainer;
    private ConstraintLayout coursesLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home,container,false);
        initializeViews(view);
        configureRecyclerView();
        setupSearchView();
        viewModel = new ViewModelProvider(this).get(BookViewModel.class);
        observeChange();

        return view;
    }
    private void configureRecyclerView() {
        bookListAdapter = new BookListAdapter(this,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL,
                false));
        recyclerView.setAdapter(bookListAdapter);
    }

    private void initializeViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerView_home);
        searchView = view.findViewById(R.id.search_view_home);
        recyclerContainer = view.findViewById(R.id.recyclerView_container);
        coursesLayout = view.findViewById(R.id.courses_id_home);

    }
    private void setupSearchView() {
        searchView.setOnSearchClickListener(v -> {
            recyclerView.setVisibility(View.VISIBLE);
            viewModel.getItems();
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                bookListAdapter.setBookList(null);
                recyclerContainer.setVisibility(View.VISIBLE);
                coursesLayout.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                return false;
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                recyclerContainer.setVisibility(View.GONE);
                coursesLayout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                viewModel.searchBookApi(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
    public void searchBookApi(String query){
        viewModel.searchBookApi(query);

    }

    private void observeChange(){
        viewModel.getItems().observe(getViewLifecycleOwner(), new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                for (Item item :
                        items) {
                    bookListAdapter.setBookList(items);
                    try {
//                        Log.d("CheckItems", "onChanged: "+item.getVolumeInfo().getImageLinks().getSmallThumbnail()+".jpg");
//                        Log.d("CheckItems", "onChanged: "+item.getVolumeInfo().getTitle());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    @Override
    public void onBookClickListener(int position) {
        Fragment bookDetailsFragment = new BookDetailsFragment();
        Bundle bundle = new Bundle();
        Item book = bookListAdapter.getSelectedBook(position);
        bundle.putParcelable("books",book);
      //  Log.d("TAGY", "onBookClickListener: "+bookListAdapter.getSelectedBook(position).getVolumeInfo().getTitle());
        bookDetailsFragment.setArguments(bundle);
        Item getParcel = bundle.getParcelable("books");
        Toast.makeText(getContext(), getParcel.getVolumeInfo().getTitle(), Toast.LENGTH_SHORT).show();

        requireActivity().getSupportFragmentManager().beginTransaction()
                .remove(new HomeFragment())
                .replace(R.id.nav_host_fragment_content_information, bookDetailsFragment)
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit();


    }

}