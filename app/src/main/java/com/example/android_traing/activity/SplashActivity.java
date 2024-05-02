package com.example.android_traing.activity;

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
import com.example.android_traing.R;

public class SplashActivity extends AppCompatActivity {

    private boolean isSkip;
    private boolean isBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView imageView = findViewById(R.id.imageView);
        Glide.with(this).load("http://5b0988e595225.cdn.sohucs.com/images/" +
                "20190831/05de49d16e374e9e997bc97fdf29b0cc.gif").into(imageView);
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