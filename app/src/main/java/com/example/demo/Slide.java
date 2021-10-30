package com.example.demo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Slide extends AppCompatActivity {

    ViewPagerAdapter viewPagerAdapter;
    ViewPager slideView;
    Button backBtn,nextBtn,skipBtn;
    TextView[]dots;
    ConstraintLayout dotsLayout;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        backBtn =findViewById(R.id.Backbtn);
        nextBtn =findViewById(R.id.Nextbtn);
        skipBtn =findViewById(R.id.SkipBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(getItem(0)>0)
                {
                    slideView.setCurrentItem(getItem(-1),true);
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(getItem(0)<3)
                {
                    slideView.setCurrentItem(getItem(1),true);
                }
                else
                {
                    Intent i = new Intent(Slide.this,Login.class);
                    startActivity(i);
                    finish();
                }

            }
        });

        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Slide.this,Login.class);
                startActivity(i);
            }
        });



        slideView=(ViewPager) findViewById(R.id.sliderPage);

        dotsLayout=(ConstraintLayout)findViewById(R.id.indicator);

        viewPagerAdapter = new ViewPagerAdapter(this);
        slideView.setAdapter(viewPagerAdapter);
        setIndicator(0);
        slideView.addOnPageChangeListener(viewChange);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setIndicator(int position)
    {
        dots = new TextView[4];
        dotsLayout.removeAllViews();

        for (int i=0;i<dots.length;i++)
        {
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.inactive,getApplicationContext().getTheme()));
            dotsLayout.addView(dots[i]);
        }
        dots[position].setTextColor(getResources().getColor(R.color.active,getApplicationContext().getTheme()));
    }

    ViewPager.OnPageChangeListener viewChange = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onPageSelected(int position) {
           setIndicator(position);
           if(position > 0)
           {
               backBtn.setVisibility(View.VISIBLE);

           }
           else {
               backBtn.setVisibility(View.INVISIBLE);
           }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private  int getItem(int i)
    {
        return slideView.getCurrentItem()+i;
    }
}