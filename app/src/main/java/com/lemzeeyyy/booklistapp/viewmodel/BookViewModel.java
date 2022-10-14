package com.lemzeeyyy.booklistapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.lemzeeyyy.booklistapp.model.Item;
import com.lemzeeyyy.booklistapp.repositories.BookRepository;

import java.util.List;

public class BookViewModel extends ViewModel {
    private BookRepository bookRepository;

    public BookViewModel() {
        bookRepository = BookRepository.getInstance();
    }
    public void searchBookApi(String query){
        bookRepository.setBookApi(query);
    }
    public LiveData<List<Item>> getItems(){
        return bookRepository.getItems();
    }
}
