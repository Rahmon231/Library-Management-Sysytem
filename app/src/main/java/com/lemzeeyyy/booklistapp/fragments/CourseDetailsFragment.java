package com.lemzeeyyy.booklistapp.fragments;

import static com.lemzeeyyy.booklistapp.activities.InformationActivity.getBookItem;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lemzeeyyy.booklistapp.R;
import com.lemzeeyyy.booklistapp.model.Item;

import java.util.List;


public class CourseDetailsFragment extends Fragment {
    private ImageView bookDetailsImageView;
    private TextView bookDetailsTextView;
    private TextView authorDetailsTextView;
    private TextView publisherTextView;
    private TextView publisherDateTextView;
    private TextView numberOfPagesTextView;
    private TextView descriptionTextView;
    private View view;
    private Item courseItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         view = inflater.inflate(R.layout.fragment_course_details, container, false);
         getDataFromIntent();
        return view;
    }
    private void getDataFromIntent(){

        try {
            Intent intent = this.getActivity().getIntent();
            Bundle bundle = intent.getBundleExtra("bandle");
            courseItem = bundle.getParcelable("courseitemmm");
        } catch (Exception e) {
            e.printStackTrace();
        }

        initializeFragmentViews(view);
        setFragmentViewItems(courseItem);

    }

    private void initializeFragmentViews(View view){
        bookDetailsImageView = view.findViewById(R.id.course_image);
        bookDetailsTextView = view.findViewById(R.id.course_title);
        authorDetailsTextView = view.findViewById(R.id.course_author_text_view);
        publisherTextView = view.findViewById(R.id.course_publisher_text_view);
        publisherDateTextView = view.findViewById(R.id.course_publisherDate_text_view);
        numberOfPagesTextView = view.findViewById(R.id.course_pages_text_view);
        descriptionTextView = view.findViewById(R.id.course_description);
    }

    private void setFragmentViewItems(Item bookItem){
        bookDetailsTextView.setText(bookItem.getVolumeInfo().getTitle());
        List<String> bookAuthors = bookItem.getVolumeInfo().getAuthors();
        authorDetailsTextView.setText(String.valueOf(bookAuthors));
        publisherTextView.setText(bookItem.getVolumeInfo().getPublisher());
        publisherDateTextView.setText(bookItem.getVolumeInfo().getPublishedDate());
        descriptionTextView.setText(bookItem.getVolumeInfo().getDescription());
        try {
            if (bookItem.getVolumeInfo().getImageLinks().getSmallThumbnail() != null) {
                String pictureUrl = bookItem.getVolumeInfo().getImageLinks().getSmallThumbnail();
                StringBuilder stringBuilder = new StringBuilder(pictureUrl);
                stringBuilder.insert(4, "s");
                String picUrl = stringBuilder.toString();
                Glide.with(this).
                        load(picUrl)
                        .into(bookDetailsImageView);
            }    else {
                bookDetailsImageView.setImageResource(R.drawable.ic_launcher_background);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}