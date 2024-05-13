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
        Glide.with(this)
                .load("https://cdn.acwing.com/media/article/image/2024/05/13/180697_83f1591210-webwxgetmsgimg.jpg")
                .centerCrop()
                .fitCenter()
                .override(500, 500) // 指定图片的宽度和高度
                .preload();

    }
}