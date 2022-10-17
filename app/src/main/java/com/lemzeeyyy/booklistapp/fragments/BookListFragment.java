package com.lemzeeyyy.booklistapp.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lemzeeyyy.booklistapp.R;
import com.lemzeeyyy.booklistapp.adapter.BookListAdapter;
import com.lemzeeyyy.booklistapp.click_listeners.BookClickListener;
import com.lemzeeyyy.booklistapp.model.Item;
import com.lemzeeyyy.booklistapp.viewmodel.BookViewModel;

import java.util.List;


public class BookListFragment extends Fragment implements BookClickListener {
    private BookViewModel viewModel;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private BookListAdapter bookListAdapter;

    public BookListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_book_list,container,false);
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
        recyclerView = view.findViewById(R.id.recyclerView);
        searchView = view.findViewById(R.id.search_view);

    }
    private void setupSearchView() {
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getItems();
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                bookListAdapter.setBookList(null);
                return false;
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
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
        Log.d("TAGY", "onBookClickListener: "+bookListAdapter.getSelectedBook(position).getVolumeInfo().getTitle());
        bookDetailsFragment.setArguments(bundle);
        requireActivity().getSupportFragmentManager().beginTransaction().remove(new BookListFragment())
                .replace(R.id.fragment_container_id, bookDetailsFragment)
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit();

    }

}