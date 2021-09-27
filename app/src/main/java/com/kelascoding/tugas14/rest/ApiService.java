package com.kelascoding.tugas14.rest;

import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.kelascoding.tugas14.model.Course;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("get_data_course.php?id")
    Call<List<Course>> getCourse();

    @FormUrlEncoded
    @POST("insert_course.php")
    Call<Course> AddCourse(
            @Field("name_course") String nameCourse,
            @Field("chapter") String chapter,
            @Field("instructor") String instructor,
            @Field("media") String media
    );

}
