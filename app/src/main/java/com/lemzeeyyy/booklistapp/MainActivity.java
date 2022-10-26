package com.lemzeeyyy.booklistapp;

import static com.lemzeeyyy.booklistapp.InformationActivity.bookItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.lemzeeyyy.booklistapp.adapter.BookListAdapter;
import com.lemzeeyyy.booklistapp.click_listeners.ItemListener;
import com.lemzeeyyy.booklistapp.fragments.BookDetailsFragment;
import com.lemzeeyyy.booklistapp.fragments.BookListFragment;
import com.lemzeeyyy.booklistapp.model.Item;
import com.lemzeeyyy.booklistapp.viewmodel.BookViewModel;

public class MainActivity extends AppCompatActivity {

    private BookViewModel viewModel;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private BookListAdapter bookListAdapter;
    InformationActivity informationActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            BookDetailsFragment bookDetailsFragment = new BookDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("books",bookItem);
            bookDetailsFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_id, BookDetailsFragment.class, null)
                    .commit();
        }
        /*initializeViews();
        configureRecyclerView();
        setupSearchView();
        viewModel = new ViewModelProvider(this).get(BookViewModel.class);
        observeChange();
        getBookSearchResponse();
        */


    }



  /*  private void setupSearchView() {
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
   */


   /* private void configureRecyclerView() {
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

    */


    }
