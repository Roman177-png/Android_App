package com.example.projekt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Viewer1Activity extends AppCompatActivity {



    private Handler slideHandler = new Handler();

    private ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer1);

        final ImageView backBtn = findViewById(R.id.backBtn);
        final TextView selectWyklad = findViewById(R.id.selectedWyklad);
        final String selectedWyklad = getIntent().getStringExtra("selectedWyklad");
        final ImageView btn_left = findViewById(R.id.btn_left);
        final ImageView btn_right = findViewById(R.id.btn_right);
        selectWyklad.setText(selectedWyklad);

        viewPager2 = findViewById(R.id.viewPagerImageSlider);

        List<SliderItem> sliderItems = new ArrayList<>();
        switch (selectedWyklad){
            case "wyklad1":
                sliderItems.add(new SliderItem(R.drawable.image_1));
                sliderItems.add(new SliderItem(R.drawable.image_2));
                sliderItems.add(new SliderItem(R.drawable.image_3));
                sliderItems.add(new SliderItem(R.drawable.image_4));
                sliderItems.add(new SliderItem(R.drawable.image_5));
                sliderItems.add(new SliderItem(R.drawable.image_6));
                break;
            case "wyklad2":
                sliderItems.add(new SliderItem(R.drawable.java_fx_1));
                sliderItems.add(new SliderItem(R.drawable.java_fx_2));
                sliderItems.add(new SliderItem(R.drawable.java_fx_3));
                sliderItems.add(new SliderItem(R.drawable.java_fx_4));
                sliderItems.add(new SliderItem(R.drawable.java_fx_5));
                sliderItems.add(new SliderItem(R.drawable.java_fx_6));
                sliderItems.add(new SliderItem(R.drawable.java_fx_7));
                sliderItems.add(new SliderItem(R.drawable.java_fx_8));
                sliderItems.add(new SliderItem(R.drawable.java_fx_9));
                break;
            case "wyklad3":
                sliderItems.add(new SliderItem(R.drawable.spring_1));
                sliderItems.add(new SliderItem(R.drawable.spring_2));
                sliderItems.add(new SliderItem(R.drawable.spring_3));
                sliderItems.add(new SliderItem(R.drawable.spring_4));
                sliderItems.add(new SliderItem(R.drawable.spring_5));
                sliderItems.add(new SliderItem(R.drawable.spring_6));
                sliderItems.add(new SliderItem(R.drawable.spring_7));
                sliderItems.add(new SliderItem(R.drawable.spring_8));
                break;
            case "wyklad4":
                sliderItems.add(new SliderItem(R.drawable.java_ee_1));
                sliderItems.add(new SliderItem(R.drawable.java_ee_2));
                sliderItems.add(new SliderItem(R.drawable.java_ee_3));
                sliderItems.add(new SliderItem(R.drawable.java_ee_4));
                sliderItems.add(new SliderItem(R.drawable.java_ee_5));
                break;
        }
        //
        viewPager2.setAdapter(new SliderAdapter(sliderItems,viewPager2));
        //
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(1.75f + r * 0.9f);
                //page.setScaleY(0.85f + r * 0.15f);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);
//
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.removeCallbacks(sliderRunnable);
                slideHandler.postDelayed(sliderRunnable,5000);
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Viewer1Activity.this,wykladActivity.class));
                finish();
            }
        });
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
            }
        });
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() - 1);
            }
        });
    }

    //
    @Override
    public void onBackPressed() {
        startActivity(new Intent(Viewer1Activity.this,wykladActivity.class));
        finish();
    }
    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        slideHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        slideHandler.postDelayed(sliderRunnable,3000);
    }
}