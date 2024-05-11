package com.example.android_cjj118.ui.me.Constenllation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.android_cjj118.R;
import com.example.android_cjj118.base.BaseFragment2;
import com.example.android_cjj118.bean.ConstellationEnumBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConstenllationFragment extends BaseFragment2 {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_constenllation, container, false);
        GridView gridView = root.findViewById(R.id.constellation_gridView);
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for (ConstellationEnumBean constellation: ConstellationEnumBean.values()) {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("itemImage",constellation.getImageID());
            map.put("itemText",constellation.getName());
            list.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(getActivity(),list,R.layout.item_constenllation_menu,
                new String[]{"itemImage","itemText"},new int[]{R.id.imageView,R.id.item_text});
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String,Object> map = (HashMap<String, Object>) gridView.getItemAtPosition(position);
                String itemName = (String) map.get("itemText");
                Toast.makeText(root.getContext(), "你点击了"+itemName, Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }
}