package com.lemzeeyyy.booklistapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lemzeeyyy.booklistapp.R;
import com.lemzeeyyy.booklistapp.click_listeners.BookClickListener;
import com.lemzeeyyy.booklistapp.model.Item;

import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookListViewHolder> {
    private List<Item> itemList;
    BookClickListener onBookClickLister;
    Context context;

    public BookListAdapter(BookClickListener onBookClickLister, Context context) {
        this.onBookClickLister =  onBookClickLister;
        this.context = context;
    }

    @NonNull
    @Override
    public BookListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_list,parent,false);
        return new BookListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookListViewHolder holder, int position) {

        if(itemList!=null) {
            holder.setBookInfo(itemList.get(position));
        }else {
            Toast.makeText(context, "API Response is null("+itemList.size()+")", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        if(itemList!=null){
            return itemList.size();
        }
        return 0;
    }


    public void setBookList(List<Item> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }
    public Item getSelectedBook(int position){
        if (itemList != null){
            if (itemList.size() > 0){
                return itemList.get(position);
            }
        }
        return  null;
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

                bookTitle.setText(item.getVolumeInfo().getTitle());
                bookPublisher.setText(item.getVolumeInfo().getPublisher());
                bookDate.setText(item.getVolumeInfo().getPublishedDate());
                bookPageCount.setText(String.valueOf(item.getVolumeInfo().getPageCount()));
                try {
                    if(item.getVolumeInfo().getImageLinks().getSmallThumbnail()!=null) {
                        String pictureUrl = item.getVolumeInfo().getImageLinks().getSmallThumbnail();
                        StringBuilder stringBuilder = new StringBuilder(pictureUrl);
                        stringBuilder.insert(4, "s");
                        String picUrl = stringBuilder.toString();

                        Glide.with(context)
                                .load(picUrl)
                                .into(bookImage);
                    }else {
                        bookImage.setImageResource(R.drawable.ic_launcher_background);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

        }


        @Override
        public void onClick(View v) {
            onBookClickLister.onBookClickListener(getAdapterPosition());
        }
    }
}
