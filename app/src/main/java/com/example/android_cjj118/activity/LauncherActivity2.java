package com.example.android_cjj118.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.SharedMemory;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.android_cjj118.R;

public class LauncherActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(() ->{
            SharedPreferences sharedPreferences = getSharedPreferences("setting",MODE_PRIVATE);
            boolean isFisrt = sharedPreferences.getBoolean("isFirst",true);
            if(isFisrt) {
                Intent intent = new Intent(LauncherActivity2.this, IntroActivity.class);
                startActivity(intent);
                sharedPreferences.edit().putBoolean("isFirst", false).apply();
            } else {
                Intent intent = new Intent(LauncherActivity2.this, SplashActivity.class);
                startActivity(intent);
            }
            finish();
        },1000);
        Glide.with(this).load("http://5b0988e595225.cdn.sohucs.com/images/" +
                "20190831/05de49d16e374e9e997bc97fdf29b0cc.gif").preload();
    }
}