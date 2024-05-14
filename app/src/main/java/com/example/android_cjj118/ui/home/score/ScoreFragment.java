package com.example.android_cjj118.ui.home.score;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.android_cjj118.R;
import com.example.android_cjj118.adapter.CourseAdapter;
import com.example.android_cjj118.bean.Course;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ScoreFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_score, container, false);

        ArrayList<Course> data = new ArrayList<>();
        data.add(new Course(R.drawable.p11, "大六壬", 10.0f));
        data.add(new Course(R.drawable.p44, "七政四余", 9.9f));
        data.add(new Course(R.drawable.p00, "四柱八字", 8.8f));
        data.add(new Course(R.drawable.p22, "紫薇斗数", 7.7f));
        data.add(new Course(R.drawable.p55, "金锁玉关", 6.6f));
        data.add(new Course(R.drawable.p77, "太公奇门", 5.5f));
        data.add(new Course(R.drawable.p66, "飞宫小奇门", 4.4f));

        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        CourseAdapter courseAdapter = new CourseAdapter(data);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        root.findViewById(R.id.floatingActionButton).setOnClickListener(v -> {
            courseAdapter.add(new Course(R.drawable.p33, "太公阴符术", 10.0f));
            Snackbar.make(v, "课程更新了快去看看吧！", Snackbar.LENGTH_INDEFINITE)
                    .setAction("确定", v1 -> Toast.makeText(getContext(),
                            "注意",
                            Toast.LENGTH_SHORT).show())
                    .show();
        });

        return root;
    }
}
