package com.lemzeeyyy.booklistapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.lemzeeyyy.booklistapp.activities.LoginActivity;
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
        searchView.setOnSearchClickListener(v -> viewModel.getItems());
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

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }
        return false;
    }

}
