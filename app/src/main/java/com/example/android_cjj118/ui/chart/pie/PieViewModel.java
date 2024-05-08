package com.example.android_cjj118.ui.chart.pie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_cjj118.bean.PieBean;

import java.util.ArrayList;
import java.util.List;

public class PieViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<List<PieBean>> pieList;

    public PieViewModel() {
        String[] salaries = new String[]{"月薪8k-15k","月薪15-30k","月薪30-100k", "月薪100k+"};
        int[] percentage = {50,25,15,10};
        pieList = new MutableLiveData<>();
        List<PieBean> value = new ArrayList<>();
        for (int i = 0; i < salaries.length; i++) {
            value.add(new PieBean(salaries[i],percentage[i]));
        }
        pieList.setValue(value);

    }
    public LiveData<List<PieBean>> getPieList() {
        return pieList;
    }
}