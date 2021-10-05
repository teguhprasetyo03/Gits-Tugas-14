package com.kelascoding.tugas14.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.kelascoding.tugas14.R;
import com.kelascoding.tugas14.adapter.CourseAdapter;
import com.kelascoding.tugas14.model.Course;
import com.kelascoding.tugas14.rest.ApiConfig;
import com.kelascoding.tugas14.rest.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private String TAG = "mvvm";
    private RecyclerView rvCourse = null;
    ApiService apiService;
    private MutableLiveData<List<Course>> mutableLiveData;
    ImageSlider imageSlider;

    public HomeFragment() {
        // Required empty public constructor
    }

    @SuppressLint("FragmentLiveDataObserve")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        rvCourse = root.findViewById(R.id.home_fragment);
        imageSlider = root.findViewById(R.id.image_slider);
        ArrayList<SlideModel> images = new ArrayList<>();
        images.add(new SlideModel(R.drawable.chem, "Chemistry", ScaleTypes.CENTER_CROP));
        images.add(new SlideModel(R.drawable.geo, "Geography", ScaleTypes.CENTER_CROP));
        images.add(new SlideModel(R.drawable.py, "Physics", ScaleTypes.CENTER_CROP));
        images.add(new SlideModel(R.drawable.math, "Mathematics", ScaleTypes.CENTER_CROP));
        imageSlider.setImageList(images);
        rvCourse.setHasFixedSize(true);
        rvCourse.setLayoutManager(new LinearLayoutManager(getActivity()));
        getData();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void getConnect() {
        apiService = ApiConfig.getApiService();
        apiService.getCourse().enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                List<Course> list = response.body();
                rvCourse.setAdapter(new CourseAdapter(getContext(), list));
                Log.d(TAG, "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage() + "|" +
                        t.getMessage());

            }
        });


    }

    public LiveData<List<Course>> getData() {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<List<Course>>();
            getConnect();
        }

        return mutableLiveData;
    }
}