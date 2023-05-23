package com.example.ecomm_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

public class AppIntro extends com.github.paolorotolo.appintro.AppIntro {


    @Nullable
    @Override
    public void init(Bundle savedInstanceState) {
        addSlide(SampleSlide.newInstance(R.layout.slide1));
        addSlide(SampleSlide.newInstance(R.layout.slide2));
        addSlide(SampleSlide.newInstance(R.layout.slide3));

        setIndicatorColor(Color.parseColor("#FF9800"), Color.parseColor("#855002"));
        setColorSkipButton(Color.parseColor("#FF9800"));
        setColorDoneText(Color.parseColor("#FF9800"));
        setNextArrowColor(Color.parseColor("#FF9800"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onDonePressed() {
        gotoHome();
    }

    @Override
    public void onSkipPressed() {
        gotoHome();
    }

    public void gotoHome() {
        startActivity(new Intent(AppIntro.this, OTP_Screen.class));
    }


}