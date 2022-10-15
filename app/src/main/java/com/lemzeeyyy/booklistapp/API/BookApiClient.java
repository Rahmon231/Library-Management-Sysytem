package com.lemzeeyyy.booklistapp.API;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.lemzeeyyy.booklistapp.model.BookObject;
import com.lemzeeyyy.booklistapp.model.Item;
import com.lemzeeyyy.booklistapp.request.Service;
import com.lemzeeyyy.booklistapp.response.BookSearchResponse;
import com.lemzeeyyy.booklistapp.utils.AppExecutors;
import com.lemzeeyyy.booklistapp.utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class BookApiClient {
    private MutableLiveData<List<Item>> bookObject;

    private static BookApiClient instance;

    private RetrieveBookRunnable retrieveBookRunnable;

    public static BookApiClient getInstance() {
        if(instance == null){
            instance = new BookApiClient();
        }
        return instance;
    }

    public BookApiClient() {
        bookObject = new MutableLiveData<>();
    }
    public LiveData<List<Item>> getItems(){
        // Log.d("MoviesApi", "getMovies: "+myMovies.getValue().size());
        return bookObject;
    }
    public void searchBookApi(String query){
        if (retrieveBookRunnable!=null){
            retrieveBookRunnable = null;
        }
        retrieveBookRunnable = new RetrieveBookRunnable(query);


        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveBookRunnable);
        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //Canceling the retrofit call
                myHandler.cancel(true);
            }
        },5000, TimeUnit.MILLISECONDS);
    }

    private class RetrieveBookRunnable implements Runnable{
        private String query;
        private Boolean cancelRequest;

        public RetrieveBookRunnable(String query) {
            this.query = query;
            cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Response response = getBooks(query).execute();
                if(cancelRequest){
                    return;
                }
                if (response.code() == 200){
                    List<Item> items = new ArrayList<>(((BookSearchResponse)response.body()).getItems());
                    bookObject.postValue(items);

                }else {
                    Log.d("BookClientApiError", "run: "+response.errorBody().toString());
                }

            } catch (IOException e) {
                e.printStackTrace();
                bookObject.postValue(null);
            }
        }
        private Call<BookSearchResponse> getBooks(String query){

            return Service.getBookApi().searchBooks(
                    query,
                    Credentials.API_KEY
            );

        }
        private void setCancelRequest(){
            Log.d("CancelReq", "setCancelRequest: Cancelling Search Request");
            cancelRequest = true;
        }
    }
}
