package com.universlsoftware.nakathpathraya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.universlsoftware.nakathpathraya.onborditems.OnboardAdapter;
import com.universlsoftware.nakathpathraya.onborditems.OnboardItem;

import java.util.ArrayList;
import java.util.List;

public class OnbordActivity extends AppCompatActivity {

    private OnboardAdapter onboardAdapter;
    private LinearLayout layoutOnboardingIndicators;

    private MaterialButton buttonOnbordIngAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onbord);


        layoutOnboardingIndicators = findViewById(R.id.layoutOnboardingIndicators);
        setuponboadItem();

        ViewPager2 OnbordviewPager = findViewById(R.id.onboardViewPager);
        OnbordviewPager.setAdapter(onboardAdapter);
        buttonOnbordIngAction = findViewById(R.id.buttonOnboardAction);
        setupOnbordinfIndicators();


        OnbordviewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboradingIndicator(position);
            }
        });


        buttonOnbordIngAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OnbordviewPager.getCurrentItem() + 1 < onboardAdapter.getItemCount()) {
                    OnbordviewPager.setCurrentItem(OnbordviewPager.getCurrentItem() + 1);
                } else {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        });


    }


    private void setuponboadItem() {
        List<OnboardItem> onboardItems = new ArrayList<>();

        OnboardItem itemplayOnline = new OnboardItem();
        itemplayOnline.setTitle("ආයුබෝවන්");
        itemplayOnline.setDescription("සාදරයෙන් පිලිගන්නවා  නැකැත් පත්\u200Dරය  වෙත ");
        itemplayOnline.setImage(R.drawable.logonakath);

        OnboardItem itemplayOnline2 = new OnboardItem();
        itemplayOnline2.setTitle("දවසේ සුභ වෙලාව");
        itemplayOnline2.setDescription("සුභ මුහුර්ථ දවසේ ශුභ කාලය");
        itemplayOnline2.setImage(R.drawable.app_ic_trs);

        OnboardItem itemplayOnline3 = new OnboardItem();
        itemplayOnline3.setTitle("2023 අළුත් අවුරුදු නැකැත් ");
        itemplayOnline3.setDescription("දැන් පහුසුවෙන්ම සිංහල අලුත් අවුරුදු නැකැත්.");
        itemplayOnline3.setImage(R.drawable.flashimage);




        onboardItems.add(itemplayOnline);
        onboardItems.add(itemplayOnline2);
        onboardItems.add(itemplayOnline3);

        onboardAdapter = new OnboardAdapter(onboardItems);
    }

    private void setupOnbordinfIndicators() {
        ImageView[] indicators = new ImageView[onboardAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8, 0, 8, 0);

        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(), R.drawable.onboarding_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicators.addView(indicators[i]);

        }
    }

    private void setCurrentOnboradingIndicator(int index) {
        int childCount = layoutOnboardingIndicators.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutOnboardingIndicators.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.onbordiing_indicator_active));

            } else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_inactive)
                );

            }
        }
        if (index == onboardAdapter.getItemCount() - 1) {
            buttonOnbordIngAction.setText("Start");
        }else
        {
            buttonOnbordIngAction.setText("Next ");
        }
    }


}