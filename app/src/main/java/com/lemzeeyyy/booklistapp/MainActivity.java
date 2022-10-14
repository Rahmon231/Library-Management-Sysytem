package com.lemzeeyyy.booklistapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lemzeeyyy.booklistapp.API.BookApi;
import com.lemzeeyyy.booklistapp.model.BookObject;
import com.lemzeeyyy.booklistapp.model.Item;
import com.lemzeeyyy.booklistapp.request.Service;
import com.lemzeeyyy.booklistapp.response.BookSearchResponse;
import com.lemzeeyyy.booklistapp.utils.Credentials;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             getBookSearchResponse();
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
            public void onResponse(Call<BookSearchResponse> call, @NonNull Response<BookSearchResponse> response) {
                if (response.code() == 200){
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
}