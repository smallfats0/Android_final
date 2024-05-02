package com.example.android_traing.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.android_traing.bean.NewsBean;
import com.example.android_traing.utils.NetUtils;
import com.github.leonardoxh.livedatacalladapter.Resource;


import java.util.List;

import okhttp3.Response;

public class HomeViewModel extends ViewModel {
    public LiveData<List<NewsBean>> getNewsList() {
        return Transformations.map(NetUtils.get().getNewsList(), Resource::getResource);
    }
    public LiveData<List<NewsBean>> getAdList() {
        return Transformations.map(NetUtils.get().getAdList(),Resource::getResource);
    }
}