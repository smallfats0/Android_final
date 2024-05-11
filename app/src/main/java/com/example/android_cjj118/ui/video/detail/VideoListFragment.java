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
    private String url0="https://vd3.bdstatic.com/mda-mgmrsyu6ck4p521p/hd/cae_h264/1626998511442986356/mda-mgmrsyu6ck4p521p.mp4";
    private String url1="https://vd3.bdstatic.com/mda-nhnfy3qn810yh98h/576p/h264/1661253451137588795/mda-nhnfy3qn810yh98h.mp4";
    private String url2="https://vd3.bdstatic.com/mda-pggm42xnneg614f2/576p/h264/1689605721557377864/mda-pggm42xnneg614f2.mp4";
    private String url3="https://vd3.bdstatic.com/mda-nh4me7s3jvg4df5y/576p/h264/1659712595400074050/mda-nh4me7s3jvg4df5y.mp4";
    private String url4="https://vd3.bdstatic.com/mda-qckday1hugpfwr6e/576p/h264/1711013172106373013/mda-qckday1hugpfwr6e.mp4";
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
            if (position%5==0)
                videoDetailFragment.playNewUrl(url0);
            else if (position%5==1)
                videoDetailFragment.playNewUrl(url1);
            else if (position%5==2)
                videoDetailFragment.playNewUrl(url2);
            else if (position%5==3)
                videoDetailFragment.playNewUrl(url3);
            else if (position%5==4)
                videoDetailFragment.playNewUrl(url4);
        });
        return root;
    }
}