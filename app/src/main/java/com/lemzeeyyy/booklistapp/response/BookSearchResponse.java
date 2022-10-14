package com.lemzeeyyy.booklistapp.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lemzeeyyy.booklistapp.model.Item;

import java.util.List;

public class BookSearchResponse {
    @SerializedName("totalItems")
    @Expose
    private Integer totalItems;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "BookSearchResponse{" +
                "totalItems=" + totalItems +
                ", items=" + items +
                '}';
    }
}
