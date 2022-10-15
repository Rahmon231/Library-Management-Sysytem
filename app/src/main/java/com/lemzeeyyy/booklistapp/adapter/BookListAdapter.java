package com.lemzeeyyy.booklistapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lemzeeyyy.booklistapp.R;
import com.lemzeeyyy.booklistapp.click_listeners.BookClickListener;
import com.lemzeeyyy.booklistapp.model.Item;

import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookListViewHolder> {
    private List<Item> itemList;
    BookClickListener onBookClickLister;

    public BookListAdapter(BookClickListener onBookClickLister) {
        this.onBookClickLister =  onBookClickLister;
    }

    @NonNull
    @Override
    public BookListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_list,parent,false);
        return new BookListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookListViewHolder holder, int position) {
        holder.setBookInfo(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public class BookListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView bookImage;
        private TextView bookTitle, bookDate, bookPublisher, bookPageCount;
        public BookListViewHolder(@NonNull View itemView) {
            super(itemView);
            bookTitle = itemView.findViewById(R.id.idTVBookTitle);
            bookDate = itemView.findViewById(R.id.idTVDate);
            bookPublisher = itemView.findViewById(R.id.idTVpublisher);
            bookPageCount = itemView.findViewById(R.id.idTVPageCount);
            bookImage = itemView.findViewById(R.id.idIVbook);
            itemView.setOnClickListener(this);
        }

        public void setBookInfo(Item item) {
            bookImage.setImageResource(R.drawable.ic_launcher_background);
            bookTitle.setText(item.getVolumeInfo().getTitle());
            bookPublisher.setText(item.getVolumeInfo().getPublisher());
            bookDate.setText(item.getVolumeInfo().getPublishedDate());
            bookPageCount.setText(String.valueOf(item.getVolumeInfo().getPageCount()));

        }

        @Override
        public void onClick(View v) {
            onBookClickLister.onBookClickListener(getAdapterPosition());
        }
    }
}
