package com.kelascoding.tugas14.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.kelascoding.tugas14.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        TextInputLayout edtname_course = findViewById(R.id.name_course_detail);
        TextInputLayout edtchapter = findViewById(R.id.edtChapter);
        TextInputLayout edtinstructor = findViewById(R.id.instructor_detail);
        TextInputLayout edtmedia = findViewById(R.id.media_detail);
        Button btnedit = findViewById(R.id.btn_edit);
        Button btndelet = findViewById(R.id.btn_delete);

        String course = getIntent().getStringExtra("name_course");
        String chapter = getIntent().getStringExtra("chapter");
        String instructor = getIntent().getStringExtra("instructor");
        String media = getIntent().getStringExtra("media");
//        edtname_course.settext(course);
////        edtchapter.setText(chapter);
////        edtinstructor.setText(instructor);
////        edtmedia.setText(media);

        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btndelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}