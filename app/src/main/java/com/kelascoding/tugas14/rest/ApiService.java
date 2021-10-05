package com.kelascoding.tugas14.rest;

import com.kelascoding.tugas14.model.Course;
import com.kelascoding.tugas14.ui.login.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @POST("user/regist.php")
    @FormUrlEncoded
    Call<User> register(@Field("nama") String nama, @Field("email") String email
                        , @Field("username") String username, @Field("password") String password);

    @POST("user/login.php")
    @FormUrlEncoded
    Call<User> login(@Field("username") String username,
                     @Field("password") String password);


    @GET("course/get_data_course.php?id")
    Call<List<Course>> getCourse();

    @FormUrlEncoded
    @POST("course/insert_course.php")
    Call<Course> AddCourse(
            @Field("name_course") String nameCourse,
            @Field("chapter") String chapter,
            @Field("instructor") String instructor,
            @Field("media") String media
    );

}
