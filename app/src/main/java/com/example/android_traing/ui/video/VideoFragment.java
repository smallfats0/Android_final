package com.example.android_traing.ui.video;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.android_traing.R;
import com.example.android_traing.bean.VideoBean;
import com.example.android_traing.databinding.FragmentVideoBinding;
import com.example.android_traing.ui.home.HomeViewModel;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;


public class VideoFragment extends Fragment {

    private VideoViewModel videoViewModel;
    private FragmentVideoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
            videoViewModel = new ViewModelProvider(this).get(VideoViewModel.class);

        binding = FragmentVideoBinding.inflate(inflater, container, false);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        RefreshLayout refreshLayout = (RefreshLayout)root.findViewById(R.id.refreshLayout);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                getVideoList();
            }

        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                Toast.makeText(getContext(), "没有更多数据了", Toast.LENGTH_SHORT).show();
            }
        });
        getVideoList();
        return root;
    }

    private void getVideoList() {
        VideoFragment.this.videoViewModel.getVideoList().observe(VideoFragment.this.getViewLifecycleOwner(), videoBeans -> {
            for (VideoBean videoBean: videoBeans) {
                Log.i("video:", videoBean.getName());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}