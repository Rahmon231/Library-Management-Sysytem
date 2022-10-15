package com.lemzeeyyy.booklistapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.Toast;

import com.lemzeeyyy.booklistapp.API.BookApi;
import com.lemzeeyyy.booklistapp.adapter.BookListAdapter;
import com.lemzeeyyy.booklistapp.click_listeners.BookClickListener;
import com.lemzeeyyy.booklistapp.model.Item;
import com.lemzeeyyy.booklistapp.request.Service;
import com.lemzeeyyy.booklistapp.response.BookSearchResponse;
import com.lemzeeyyy.booklistapp.utils.Credentials;
import com.lemzeeyyy.booklistapp.viewmodel.BookViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  implements BookClickListener {

    private BookViewModel viewModel;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private BookListAdapter bookListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        configureRecyclerView();
        setupSearchView();
        viewModel = new ViewModelProvider(this).get(BookViewModel.class);
        observeChange();
        //getBookSearchResponse();

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

    private void configureRecyclerView() {
        bookListAdapter = new BookListAdapter(this,MainActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,
                false));
        recyclerView.setAdapter(bookListAdapter);
    }

    private void initializeViews() {
        recyclerView = findViewById(R.id.recyclerView);
       searchView = findViewById(R.id.search_view);

    }

    private void getBookSearchResponse() {
        BookApi bookApi = Service.getBookApi();
        Call<BookSearchResponse> responseCall = bookApi.searchBooks(
                "Physics",
                Credentials.API_KEY
        );
        responseCall.enqueue(new Callback<BookSearchResponse>() {
            @Override
            public void onResponse(@NonNull Call<BookSearchResponse> call, @NonNull Response<BookSearchResponse> response) {

                if (response.code() == 200){

                    assert response.body() != null;
                    BookSearchResponse bookObject = response.body();


                    for (Item books :
                            response.body().getItems()) {
                        try {
                            String pictureUrl = books.getVolumeInfo()
                                    .getImageLinks().getSmallThumbnail();
                            StringBuilder stringBuilder = new StringBuilder(pictureUrl);
                            stringBuilder.insert(4,"s");
                            Log.d("CHeckApiData", "onResponse: "+books.getVolumeInfo().getTitle());

                            Log.d("CHeckApiData", "onResponse:"+stringBuilder.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                }
            }

            @Override
            public void onFailure(Call<BookSearchResponse> call, Throwable t) {

            }
        });
    }

    public void searchBookApi(String query){
        viewModel.searchBookApi(query);

    }

    private void observeChange(){
        viewModel.getItems().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                for (Item item :
                        items) {
                    bookListAdapter.setBookList(items);
                    try {
                         Log.d("CheckItems", "onChanged: "+item.getVolumeInfo().getImageLinks().getSmallThumbnail()+".jpg");
                        Log.d("CheckItems", "onChanged: "+item.getVolumeInfo().getTitle());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    @Override
    public void onBookClickListener(int position) {
        Toast.makeText(this, "Book at position "+position, Toast.LENGTH_SHORT).show();
    }
}