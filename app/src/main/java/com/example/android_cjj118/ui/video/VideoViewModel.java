package com.example.android_cjj118.ui.video;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.android_cjj118.bean.VideoBean;
import com.example.android_cjj118.utils.NetUtils;

import java.util.List;
import com.github.leonardoxh.livedatacalladapter.Resource;
public class VideoViewModel extends ViewModel {

    public LiveData<List<VideoBean>> getVideoList() {
        return Transformations.map(NetUtils.get().getVideoList(), Resource::getResource);
    }
}