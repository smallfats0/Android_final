package com.example.android_cjj118.ui.me.music;

import com.example.android_cjj118.R;

import java.util.ArrayList;

public class MyData {
    private static final ArrayList<Song> data = new ArrayList<>();
    static {
        data.add(new Song("好久不见","陈奕迅", R.drawable.hjbj,R.raw.hjbj));
        data.add(new Song("生活不止眼前的苟且","许巍",R.drawable.shbzyqdgq,
                R.raw.shbzyqdgq));
        data.add(new Song("山丘","李宗盛",R.drawable.sq,R.raw.sq));
    }
    public static ArrayList<Song> getData(){
        return data;
    }
}
