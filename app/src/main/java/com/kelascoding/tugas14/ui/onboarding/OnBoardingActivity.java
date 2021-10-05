package com.kelascoding.tugas14.ui.onboarding;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.kelascoding.tugas14.R;
import com.kelascoding.tugas14.ui.login.activity.Login;

public class OnBoardingActivity extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout linearLayout;
    Button btnSkip, btnNext, btnBack;

    TextView[] dots;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        btnBack = findViewById(R.id.btn_back);
        btnNext = findViewById(R.id.btn_next);
        btnSkip = findViewById(R.id.btn_skip);

        btnSkip.setOnClickListener(v -> {
            Intent i = new Intent(OnBoardingActivity.this, Login.class);
            startActivity(i);
            finish();
        });

        btnNext.setOnClickListener(v -> {
            if (getItem(0) < 3) {
                viewPager.setCurrentItem(getItem(1), true);
            } else {
                Intent i = new Intent(OnBoardingActivity.this, Login.class);
                startActivity(i);
                finish();
            }

        });

        btnBack.setOnClickListener(v -> {
            if (getItem(0) > 0) {
                viewPager.setCurrentItem(getItem(-1), true);
            }
        });

        viewPager = findViewById(R.id.sliderViewPager);
        linearLayout = findViewById(R.id.indicator_layout);
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        setUpIndicator(0);
        viewPager.addOnPageChangeListener(viewListener);
    }

    @SuppressLint("NewApi")
    public void setUpIndicator(int position) {
        dots = new TextView[4];
        linearLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(50);
            dots[i].setTextColor(getResources().getColor(R.color.grey, getApplicationContext().getTheme()));
                linearLayout.addView(dots[i]);
            }
                dots[position].setTextColor(getResources().getColor(R.color.inactive, getApplicationContext().getTheme()));


        }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setUpIndicator(position);

            if (position > 0) {
                btnBack.setVisibility(View.VISIBLE);
            } else {
                btnBack.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }
}