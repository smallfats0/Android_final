package com.example.android_cjj118.ui.chart.radar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.android_cjj118.bean.LineBean;
import com.example.android_cjj118.bean.RadarBean;

import java.util.ArrayList;
import java.util.List;

public class RadarViewModel extends ViewModel {

    private MutableLiveData<List<RadarBean>> radarList;

    public RadarViewModel() {
        String[] years= new String[]{"应届生","1-2年","2-3年",
                "3-5年","5-8年","8-10年","10年"};
        radarList = new MutableLiveData<>();
        List<RadarBean> list = new ArrayList<>();

        for (int i = 0; i < years.length; i++) {
            LineBean lineBean1 = new LineBean(years[i],(int)(Math.random() * 20));
            LineBean lineBean2 = new LineBean(years[i],(int)(Math.random() * 30));
            LineBean lineBean3 = new LineBean(years[i],(int)(Math.random() * 40));
            LineBean lineBean4 = new LineBean(years[i],(int)(Math.random() * 50));
            LineBean lineBean5 = new LineBean(years[i],(int)(Math.random() * 60));
            LineBean lineBean6 = new LineBean(years[i],(int)(Math.random() * 70));
            LineBean lineBean7 = new LineBean(years[i],(int)(Math.random() * 80));
            list.add(new RadarBean(lineBean1,lineBean2,lineBean3,lineBean4,lineBean5,lineBean6,lineBean7));
        }
        radarList.setValue(list);
    }

    public LiveData<List<RadarBean>> getRadarList(){
        return radarList;
    }
}