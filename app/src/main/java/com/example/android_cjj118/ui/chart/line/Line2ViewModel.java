package com.example.android_cjj118.ui.chart.line;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_cjj118.bean.BarBean;
import com.example.android_cjj118.bean.LineBean;

import java.util.ArrayList;
import java.util.List;

public class Line2ViewModel extends ViewModel {
    private MutableLiveData<List<BarBean>> barList;

    public Line2ViewModel() {
        String[] years= new String[]{"应届生","1-2年","2-3年",
                "3-5年","5-8年","8-10年","10年"};
        int[] salaries1={6000,13000,20000,26000,35000,50000,100000};
        int[] salaries2={4000,10000,18000,32000,45000,55000,93000};
        barList = new MutableLiveData<>();
        List<BarBean> list = new ArrayList<>();

        for (int i = 0; i < years.length; i++) {
            LineBean lineBean1 = new LineBean(years[i],salaries1[i]);
            LineBean lineBean2 = new LineBean(years[i],salaries2[i]);
            list.add(new BarBean(lineBean1,lineBean2));
        }
        barList.setValue(list);
    }

    public LiveData<List<BarBean>> getLine2List(){
        return barList;
    }
}