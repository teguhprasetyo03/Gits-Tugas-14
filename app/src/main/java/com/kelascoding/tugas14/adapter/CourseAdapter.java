package com.kelascoding.tugas14.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kelascoding.tugas14.R;
import com.kelascoding.tugas14.model.Course;
import com.kelascoding.tugas14.ui.activity.DetailActivity;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    private Context context;
    private List<Course> courseList = null;

    public CourseAdapter(Context context, List<Course> courseList) {
        this.context = context;
        this.courseList = courseList;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_course, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
            holder.tvNameCouse.setText(courseList.get(position).getNameCourse());
            holder.tvInstructor.setText(courseList.get(position).getInstructor());
            holder.tvChapter.setText(courseList.get(position).getChapter());
            holder.tvMedia.setText(courseList.get(position).getMedia());

            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("course_name",courseList.get(position).getNameCourse());
                intent.putExtra("chapter",courseList.get(position).getChapter());
                intent.putExtra("instructor",courseList.get(position).getInstructor());
                intent.putExtra("media",courseList.get(position).getMedia());
                context.startActivity(intent);
            });
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameCouse, tvChapter, tvInstructor, tvMedia;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameCouse = itemView.findViewById(R.id.tv_name_course);
            tvChapter = itemView.findViewById(R.id.tv_chapter);
            tvInstructor = itemView.findViewById(R.id.tv_instructor);
            tvMedia = itemView.findViewById(R.id.tv_media);
        }
    }
}
