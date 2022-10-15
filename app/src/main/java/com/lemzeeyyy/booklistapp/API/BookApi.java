package com.lemzeeyyy.booklistapp.API;

import com.lemzeeyyy.booklistapp.model.BookObject;
import com.lemzeeyyy.booklistapp.response.BookSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookApi {

    //https://www.googleapis.com/
    // books/v1/volumes
    // ?q=physics&key=AIzaSyC2tJSHNuXu0kAmiR7cAHv6hublPcOOsRg

    @GET("books/v1/volumes")
    Call<BookSearchResponse> searchBooks(
            @Query("q") String query,
            @Query("key") String key
    );
}
