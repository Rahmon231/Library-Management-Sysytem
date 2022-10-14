package com.lemzeeyyy.booklistapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lemzeeyyy.booklistapp.API.BookApi;
import com.lemzeeyyy.booklistapp.model.Item;
import com.lemzeeyyy.booklistapp.request.Service;
import com.lemzeeyyy.booklistapp.response.BookSearchResponse;
import com.lemzeeyyy.booklistapp.utils.Credentials;
import com.lemzeeyyy.booklistapp.viewmodel.BookViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private BookViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);

        viewModel = new ViewModelProvider(this).get(BookViewModel.class);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           //  getBookSearchResponse();
                observeChange();
                searchBookApi("Physics");
            }
        });
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
                        Log.d("CHeckApiData", "onResponse: "+books.getVolumeInfo().getTitle());
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
                    Log.d("CheckItems", "onChanged: "+item.getId());
                }
            }
        });
    }
}