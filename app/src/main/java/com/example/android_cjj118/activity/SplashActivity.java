package com.example.android_cjj118.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.android_cjj118.R;

public class SplashActivity extends AppCompatActivity {

    private boolean isSkip;
    private boolean isBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView imageView = findViewById(R.id.imageView);
        Glide.with(this).load("https://s1.aigei.com/src/img/gif/c4/c4cf89e1562344078ca8d9d8900cc68b.gif?imageMogr2/auto-orient/thumbnail/!282x282r/gravity/Center/crop/282x282/quality/85/%7CimageView2/2/w/282&e=1735488000&token=P7S2Xpzfz11vAkASLTkfHN7Fw-oOZBecqeJaxypL:smun44OF-yX2vtiMlx5cGKs93Bg=").into(imageView);
        new Handler().postDelayed(() ->{
            if(!isSkip && !isBack){
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },5000);
    }

    public void skip(View view) {
        isSkip = true;
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        isBack = true;
        super.onBackPressed();
    }
}