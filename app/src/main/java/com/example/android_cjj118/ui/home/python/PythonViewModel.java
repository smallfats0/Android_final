package com.example.android_cjj118.ui.home.python;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.android_cjj118.bean.NewsBean;
import com.example.android_cjj118.bean.PythonBean;
import com.example.android_cjj118.utils.NetUtils;
import com.github.leonardoxh.livedatacalladapter.Resource;

import java.util.List;

public class PythonViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    public LiveData<List<PythonBean>> getPythonList() {
        return Transformations.map(NetUtils.get().getPythonList(), Resource::getResource);
    }
}