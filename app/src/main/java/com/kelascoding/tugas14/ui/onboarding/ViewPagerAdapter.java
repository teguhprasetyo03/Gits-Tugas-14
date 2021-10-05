package com.kelascoding.tugas14.ui.onboarding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.kelascoding.tugas14.R;

public class ViewPagerAdapter extends PagerAdapter {
    Context context;

    int [] images = {
            R.drawable.geo,
            R.drawable.math,
            R.drawable.py,
            R.drawable.chem
    };

    int [] title = {
            R.string.title_geo,
            R.string.title_math,
            R.string.title_py,
            R.string.title_chem
    };

    int [] desc ={
            R.string.geo_desc,
            R.string.math_desc,
            R.string.py_desc,
            R.string.chem_desc
    };

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view ==(LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout, container, false);

        ImageView imageView = view.findViewById(R.id.img_onboard);
        TextView tvOnboard = view.findViewById(R.id.tv_title_onboard);
        TextView tvDesc = view.findViewById(R.id.tv_desc_onboard);

        imageView.setImageResource(images[position]);
        tvOnboard.setText(title[position]);
        tvDesc.setText(desc[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
