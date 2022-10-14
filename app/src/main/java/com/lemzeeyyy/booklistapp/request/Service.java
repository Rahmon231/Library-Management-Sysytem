package com.lemzeeyyy.booklistapp.request;

import com.lemzeeyyy.booklistapp.API.BookApi;
import com.lemzeeyyy.booklistapp.utils.Credentials;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {
    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(Credentials.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();
    private static BookApi bookApi = retrofit.create(BookApi.class);

    public static BookApi getBookApi() {
        return bookApi;
    }
}
