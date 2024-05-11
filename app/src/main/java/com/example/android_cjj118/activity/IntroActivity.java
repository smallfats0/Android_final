package com.example.android_cjj118.activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.android_cjj118.R;
import com.github.appintro.AppIntro;
import com.github.appintro.AppIntroFragment;

public class IntroActivity extends AppIntro {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(AppIntroFragment.createInstance("乾为天",
                "天行健，君子以自强不息。", R.drawable.p1, android.R.color.black));
        addSlide(AppIntroFragment.createInstance("兑为泽",
                "丽泽，兑。君子以朋友讲习。", R.drawable.p2, android.R.color.black));
        addSlide(AppIntroFragment.createInstance("离为火",
                "明两作，离。大人以继明照四方。", R.drawable.p3, android.R.color.black));
        addSlide(AppIntroFragment.createInstance("震为雷",
                "洊雷，震。君子以恐惧修省。", R.drawable.p4, android.R.color.black));
        addSlide(AppIntroFragment.createInstance("巽为风",
                "随风，巽。君子以申命行事。", R.drawable.p5, android.R.color.black));
        addSlide(AppIntroFragment.createInstance("坎为水",
                "水洊至，习坎。君子以常德行，习教事。", R.drawable.p6, android.R.color.black));
        addSlide(AppIntroFragment.createInstance("艮为山",
                "兼山，艮。君子以思不出其位。", R.drawable.p7, android.R.color.black));
        addSlide(AppIntroFragment.createInstance("坤为地",
                "地势坤，君子以厚德载物。", R.drawable.p8, android.R.color.black));
        setSkipText("跳过");
        setDoneText("完成");

        setSeparatorColor(Color.WHITE);
        setImmersiveMode();
    }

    @Override
    protected void onSkipPressed(@Nullable Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDonePressed(@Nullable Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
