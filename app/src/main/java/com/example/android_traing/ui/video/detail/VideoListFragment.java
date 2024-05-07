package com.example.android_traing.ui.video.detail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_traing.R;
import com.example.android_traing.adapter.videoListAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class VideoListFragment extends Fragment {
    private List<String> list;
    private VideoDetailFragment videoDetailFragment;
    private String url0="https://vt1.doubanio.com/202405070841/f72a4090aa861f823fe11a462aa8ef92/view/movie/M/703150735.mp4";
    private String url1="https://vt1.doubanio.com/202405070842/3ac67ef578dd014d09ad7cfba974c367/view/movie/M/703150733.mp4";
    private String url2="https://vt1.doubanio.com/202405070844/6f4afecb64441f4058871646b332672f/view/movie/M/703150787.mp4";
    private String url3="https://vt1.doubanio.com/202405070843/c2b9b37e482cf4de0ae13dd01646f604/view/movie/M/703150051.mp4";
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