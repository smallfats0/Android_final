package com.example.android_cjj118.ui.me.graffiti;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.android_cjj118.R;
import com.example.android_cjj118.base.BaseFragment2;

import java.util.ArrayList;
import java.util.Random;

public class GraffitiFragment extends BaseFragment2 {

    private HandWrite handWrite = null;
    Button clear = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =inflater.inflate(R.layout.fragment_graffiti, container, false);
        handWrite = root.findViewById(R.id.hw);
        clear = root.findViewById(R.id.btn);
        Button downloadButton = root.findViewById(R.id.button_download);
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int size= random.nextInt(5);
                ArrayList urls = new ArrayList();
                urls.add("https://img95.699pic.com/xsj/05/u4/ac.jpg%21/fw/700/watermark/url/L3hzai93YXRlcl9kZXRhaWwyLnBuZw/align/southeast");
                urls.add("https://img95.699pic.com/xsj/15/fl/8j.jpg%21/fw/700/watermark/url/L3hzai93YXRlcl9kZXRhaWwyLnBuZw/align/southeast");
                urls.add("https://img95.699pic.com/xsj/1s/5k/0m.jpg%21/fw/700/watermark/url/L3hzai93YXRlcl9kZXRhaWwyLnBuZw/align/southeast");
                urls.add("https://img95.699pic.com/xsj/18/8x/x6.jpg%21/fw/700/watermark/url/L3hzai93YXRlcl9kZXRhaWwyLnBuZw/align/southeast");
                urls.add("https://img95.699pic.com/xsj/1c/4g/78.jpg%21/fw/700/watermark/url/L3hzai93YXRlcl9kZXRhaWwyLnBuZw/align/southeast");
                String imageUrl = (String) urls.get(size); // 替换为实际的图片URL

                Glide.with(getContext())
                        .asBitmap()
                        .load(imageUrl)
                        .into(new CustomTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                                handWrite.setBitmap(bitmap); // 当图片下载完成时设置Bitmap
                            }

                            @Override
                            public void onLoadCleared(Drawable placeholder) {
                                // 这里处理资源被清除的情况
                            }

                            @Override
                            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                                super.onLoadFailed(errorDrawable);
                                // 这里处理图片加载失败的情况
                            }
                        });
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handWrite.clear();
            }
        });
        return root;
    }
}