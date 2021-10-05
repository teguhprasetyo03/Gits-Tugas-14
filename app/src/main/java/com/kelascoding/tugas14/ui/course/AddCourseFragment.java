package com.kelascoding.tugas14.ui.course;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;
import com.kelascoding.tugas14.R;
import com.kelascoding.tugas14.model.Course;
import com.kelascoding.tugas14.rest.ApiConfig;
import com.kelascoding.tugas14.rest.ApiService;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCourseFragment extends Fragment {
    TextView tvDate;
    private String TAG = "retrofit";
    TextInputLayout edtname_course,
            edtchapter, edtinstructor, edtmedia;
    private Button btnadd;

    public AddCourseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_course, container, false);
        tvDate = root.findViewById(R.id.tv_date);
        edtname_course = root.findViewById(R.id.edtName);
        edtchapter = root.findViewById(R.id.edtChapter);
        edtinstructor = root.findViewById(R.id.edtInstructor);
        edtmedia = root.findViewById(R.id.edtMedia);
        btnadd = root.findViewById(R.id.btn_add);

        Date currentTime = Calendar.getInstance().getTime();
        String formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);

        String [] splitDate = formattedDate.split(",");
        tvDate.setText(splitDate[1]);

        btnadd.setOnClickListener(v -> {
            String nameCourse = String.valueOf(edtname_course.getEditText().getText());
            String chapter =  String.valueOf(edtchapter.getEditText().getText());
            String instructor = String.valueOf(edtinstructor.getEditText().getText());
            String media =  String.valueOf(edtmedia.getEditText().getText());
            AddCourse(nameCourse,chapter,instructor,media);
            Toast.makeText(getActivity(), "added successfully", Toast.LENGTH_SHORT).show();
        });
        return root;
    }

    private void AddCourse(String nameCourse, String chapter, String instructor, String media){
        ApiService apiService = ApiConfig.getApiService();
        Call<Course> AddDataCourse = apiService.AddCourse(nameCourse, chapter, instructor, media);
        AddDataCourse.enqueue(new Callback<Course>() {
            @Override
            public void onResponse(Call<Course> call, Response<Course> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onAddResponse: Add Success | > " + response.body());
                }
            }

            @Override
            public void onFailure(Call<Course> call, Throwable t) {
                Log.d(TAG, "onAddFailure: Add Failed | " + t.getMessage());

            }
        });
    }

}