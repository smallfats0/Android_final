package com.example.android_cjj118.ui.video.detail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_cjj118.R;
import com.example.android_cjj118.adapter.videoListAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class VideoListFragment extends Fragment {
    private List<String> list;
    private VideoDetailFragment videoDetailFragment;
    private String url0="https://vt1.doubanio.com/202405081118/3d3108789c52f3f92752da5647e9ad6b/view/movie/M/703140719.mp4";
    private String url1="https://vt1.doubanio.com/202405081118/5fe62f9f074d5ea671bdf37a37ccfa66/view/movie/M/703140769.mp4";
    private String url2="https://vt1.doubanio.com/202405081118/90090e066fe27fa48b47a4dd0e3d9715/view/movie/M/703140768.mp4";
    private String url3="https://vt1.doubanio.com/202405081119/823142fd88d8a15ed50bee484d3d7315/view/movie/M/703140606.mp4";
    public VideoListFragment(String[] list,VideoDetailFragment videoDetailFragment) {
        this.list = Arrays.asList(list);
        this.videoDetailFragment = videoDetailFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_video_list, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(
                getActivity(),DividerItemDecoration.VERTICAL));
        videoListAdapter videoListAdapter = new videoListAdapter(list);
        recyclerView.setAdapter(videoListAdapter);
        videoListAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (position%2==0)
                videoDetailFragment.playNewUrl(url0);
            else
                videoDetailFragment.playNewUrl(url1);
        });
        return root;
    }
}