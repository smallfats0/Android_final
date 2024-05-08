package com.example.android_cjj118.ui.chart;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_cjj118.R;
import com.example.android_cjj118.bean.BoomMenuitemBean;

import java.util.ArrayList;
import java.util.List;

public class ChartViewModel extends ViewModel {

    private  MutableLiveData<List<BoomMenuitemBean>>boomMenuItemList;

    public ChartViewModel() {
        boomMenuItemList = new MutableLiveData<>();
        List<BoomMenuitemBean> list=new ArrayList<>();
        String[] texts={"Java","PHP","Android","黑马程序员.Python",
                "黑马程序员.C/C++","黑马程序员.iOS","黑马程序员.前端与移动开发",
                "黑马程序员.UI设计","黑马程序员.网络营销"};
        int[] imageId={R.drawable.bat, R.drawable.bear,R.drawable.bee,
                R.drawable.butterfly,R.drawable.cat,R.drawable.dolphin,R.drawable.eagle,
                R.drawable.horse,R.drawable.elephant};
        boomMenuItemList = new MutableLiveData<>();
        for (int i = 0; i < texts.length; i++) {
            list.add(new BoomMenuitemBean(texts[i], imageId[i]));
        }
        boomMenuItemList.setValue(list);
    }

    public LiveData<List<BoomMenuitemBean>> getBoomMenuItemList() {
        return boomMenuItemList;
    }
}