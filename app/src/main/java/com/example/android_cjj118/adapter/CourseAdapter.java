package com.example.android_cjj118.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_cjj118.R;
import com.example.android_cjj118.bean.Course;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    private final ArrayList<Course> data;


    public CourseAdapter(ArrayList<Course> data) {
        this.data=data;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_score,
                parent, false);
        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course=data.get(position);
        holder.imageView.setImageResource(course.getImageId());
        holder.textView.setText(course.getName());
        holder.ratingBar.setRating(course.getRating());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void add(Course course) {
        data.add(0,course);
        notifyItemInserted(0);
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView textView;
        private final RatingBar ratingBar;


        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            textView=itemView.findViewById(R.id.textView);
            ratingBar=itemView.findViewById(R.id.ratingBar);
        }
    }
}
