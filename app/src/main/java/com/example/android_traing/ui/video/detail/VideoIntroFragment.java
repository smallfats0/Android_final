package com.example.android_traing.ui.video.detail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_traing.R;

public class VideoIntroFragment extends Fragment {
    private String intro;

    public VideoIntroFragment(String intro) {
        this.intro = intro;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_video_intro, container, false);

        
        return root;
    }
}