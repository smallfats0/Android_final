package com.example.android_cjj118.ui.home.fuzhou;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_cjj118.R;
import com.example.android_cjj118.adapter.PythonAdapter;
import com.example.android_cjj118.base.BaseFragment2;
import com.example.android_cjj118.ui.home.python.PythonViewModel;

import java.util.ArrayList;


public class FzFragment extends BaseFragment2 {

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_python, container, false);
        super.onCreateView(inflater, container, savedInstanceState);

        ArrayList<ChineseZodiac> data = new ArrayList<>();
        data.add(new ChineseZodiac(R.drawable.s1,"鼠符咒"));
        data.add(new ChineseZodiac(R.drawable.s2,"牛符咒"));
        data.add(new ChineseZodiac(R.drawable.s3,"虎符咒"));
        data.add(new ChineseZodiac(R.drawable.s4,"兔符咒"));
        data.add(new ChineseZodiac(R.drawable.s5,"龙符咒"));
        data.add(new ChineseZodiac(R.drawable.s6,"蛇符咒"));
        data.add(new ChineseZodiac(R.drawable.s7,"马符咒"));
        data.add(new ChineseZodiac(R.drawable.s8,"羊符咒"));
        data.add(new ChineseZodiac(R.drawable.s9,"猴符咒"));
        data.add(new ChineseZodiac(R.drawable.s10,"鸡符咒"));
        data.add(new ChineseZodiac(R.drawable.s11,"狗符咒"));
        data.add(new ChineseZodiac(R.drawable.s12,"猪符咒"));

        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        ChineseZodiacAdaptor chineseZodiacAdaptor = new ChineseZodiacAdaptor(data);
        recyclerView.setAdapter(chineseZodiacAdaptor);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemDragSwipeCallback(chineseZodiacAdaptor));
        itemTouchHelper.attachToRecyclerView(recyclerView);

        return root;
    }
}
