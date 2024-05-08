package com.example.android_cjj118.ui.video.detail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android_cjj118.R;

public class VideoIntroFragment extends Fragment {
    private String intro;
    // 无参构造函数
    public VideoIntroFragment() {
        // Required empty public constructor
    }

    public VideoIntroFragment(String intro) {
        this.intro = intro;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_video_intro, container, false);
        TextView textView = root.findViewById(R.id.textView);
        textView.setText(intro);
        return root;
    }
}