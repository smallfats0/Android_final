package com.example.android_cjj118.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_cjj118.R;
import com.example.android_cjj118.adapter.HomeAdapter;
import com.example.android_cjj118.adapter.ImageAdapter;
import com.example.android_cjj118.adapter.ImageTitleNumAdapter;
import com.example.android_cjj118.bean.NewsBean;
import com.example.android_cjj118.databinding.FragmentHomeBinding;
import com.scwang.smart.refresh.footer.BallPulseFooter;
import com.scwang.smart.refresh.header.BezierRadarHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.SpinnerStyle;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.transformer.DepthPageTransformer;
import com.youth.banner.transformer.RotateDownPageTransformer;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private HomeAdapter homeAdapter;

    private Banner<?, BannerAdapter<?,?>> banner;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        RefreshLayout refreshLayout =  root.findViewById(R.id.refreshLayout);

        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        homeAdapter = new HomeAdapter(null);
        recyclerView.setAdapter(homeAdapter);
        homeAdapter.setEmptyView(R.layout.empty_home);
        View headerView = inflater.inflate(R.layout.header_home, container, false);
        homeAdapter.addHeaderView(headerView);
        homeAdapter.setHeaderWithEmptyEnable(true);
        banner = headerView.findViewById(R.id.banner);
        banner.addBannerLifecycleObserver(this)//添加生命周期观察者
                .setBannerGalleryMZ(20,0.8f)
                .setPageTransformer(new RotateDownPageTransformer())
                .start();
//        指示器
        banner.setIndicator(new CircleIndicator(getContext()));


        List<Integer> list = new ArrayList<>();
        for(int i = 0;i<3;i++){
            list.add(R.drawable.error);
        }
        banner.setAdapter(new ImageAdapter(list));
        recyclerView.setAdapter(homeAdapter);
        refreshLayout.setRefreshHeader(new BezierRadarHeader(getContext()).setEnableHorizontalDrag(true));
        refreshLayout.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.FixedBehind));
        refreshLayout.setOnRefreshListener(layout-> {
            layout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            getAdList();
            getNewsList();
        });

        refreshLayout.setOnLoadMoreListener(refreshlayout -> {
            refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            Toast.makeText(getContext(), "没有更多数据了", Toast.LENGTH_SHORT).show();
        });
//        测试时注释下面两行代码
        getAdList();
        getNewsList();
        //       跳转到python页面
        LinearLayout linearLayout_python = headerView.findViewById(R.id.linearLayout_python);
        linearLayout_python.setOnClickListener(v ->Navigation.findNavController(v)
                .navigate(R.id.action_navigation_home_to_pythonFragment));

        // 跳转符咒
        LinearLayout linearLayout_fuzhou = headerView.findViewById(R.id.linearLayout_fuzhou);
        linearLayout_fuzhou.setOnClickListener(v ->Navigation.findNavController(v)
                .navigate(R.id.action_navigation_home_to_fzFragment));

        homeAdapter.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle=new Bundle();
            bundle.putString("url",homeAdapter.getData().get(position).getNewsUrl());
            Navigation.findNavController(view).navigate(R.id.action_navigation_home_to_webFragment,
                    bundle);
        });
        return root;
    }

    private void getAdList() {
        homeViewModel.getAdList().observe(getViewLifecycleOwner(), newsBeans -> {
            banner.setAdapter(new ImageTitleNumAdapter(newsBeans));
            banner.setOnBannerListener((data, position) -> {
                Bundle bundle = new Bundle();
                bundle.putString("url", ((NewsBean)data).getNewsUrl());
                Navigation.findNavController(banner).navigate(R.id.action_navigation_home_to_webFragment,
                        bundle);
            });
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getNewsList() {
        homeViewModel.getNewsList().observe(getViewLifecycleOwner(), newsBeans -> {
            homeAdapter.setList(newsBeans);
        });
    }
}

