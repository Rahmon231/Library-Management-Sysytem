package com.lemzeeyyy.booklistapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lemzeeyyy.booklistapp.R;
import com.lemzeeyyy.booklistapp.click_listeners.CourseClickListener;
import com.lemzeeyyy.booklistapp.viewmodel.BookViewModel;

import java.util.ArrayList;

public class CourseFragmentRecyclerAdapter
        extends RecyclerView.Adapter<CourseFragmentRecyclerAdapter.CourseViewHolder> {
    private ArrayList<String> courses;
    private Context context;
    CourseClickListener courseClickListener;

    public CourseFragmentRecyclerAdapter(ArrayList<String> courses, Context context,CourseClickListener courseClickListener) {
        this.courses = courses;
        this.context = context;
        this.courseClickListener = courseClickListener;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.course_recycler_item,parent,false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        holder.courseName.setText(courses.get(position));
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public void setCourseList(ArrayList<String> courses) {
        this.courses = courses;
        notifyDataSetChanged();
    }

    public String getSelectedCourse(int pos){
        return courses.get(pos);
    }




    public class CourseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private BookViewModel viewModel;
        private TextView courseName;
        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            courseName = itemView.findViewById(R.id.course_name);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            courseClickListener.onCourseClick(getAdapterPosition());
        }

    }
}
