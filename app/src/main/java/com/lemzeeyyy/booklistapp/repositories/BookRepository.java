package com.lemzeeyyy.booklistapp.repositories;

import androidx.lifecycle.LiveData;

import com.lemzeeyyy.booklistapp.API.BookApiClient;
import com.lemzeeyyy.booklistapp.model.Item;

import java.util.List;

public class BookRepository {
    private BookApiClient bookApiClient;
    private static BookRepository instance;
    private String mQuery;

    public static BookRepository getInstance() {
        if(instance == null){
            instance = new BookRepository();
        }
        return instance;
    }

    private BookRepository() {
        bookApiClient = BookApiClient.getInstance();
    }
    public void searchMovieApi(String query){
        mQuery = query;
        BookApiClient.getInstance().searchBookApi(query);
    }
}
