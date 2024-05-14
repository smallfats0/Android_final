package com.example.android_cjj118.ui.me.music;

import androidx.lifecycle.ViewModel;


import com.example.android_cjj118.R;

import java.util.ArrayList;

public class SongsViewModel extends ViewModel {

    private static final ArrayList<SongBean> data=new ArrayList<>();
    static {
        data.add(new SongBean("好久不见","陈奕迅", R.drawable.hjbj,R.raw.hjbj));
        data.add(new SongBean("生活不止眼前的苟且","许巍",R.drawable.shbzyqdgq,R.raw.shbzyqdgq));
        data.add(new SongBean("山丘","李宗盛",R.drawable.sq,R.raw.sq));
    }

    public static ArrayList<SongBean>getData(){
        return data;
    }
    // TODO: Implement the ViewModel
}