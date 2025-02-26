package com.example.android_cjj118.ui.video.detail;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.android_cjj118.R;
import com.example.android_cjj118.base.BaseFragment2;
import com.example.android_cjj118.utils.NetUtils;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.GSYVideoHelper;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.ArrayList;
import java.util.List;

public class VideoDetailFragment extends BaseFragment2 { StandardGSYVideoPlayer detailPlayer;

    private boolean isPlay;
    private boolean isPause;
    private String url = "https://vd3.bdstatic.com/mda-nm094r3t3hcqa2g7/hd/cae_h264/1669876815415110966/mda-nm094r3t3hcqa2g7.mp4";

    private OrientationUtils orientationUtils;
    private GSYVideoOptionBuilder gsyVideoOption;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_video_detail, container, false);
        Bundle arguments = getArguments();
        if (arguments != null) {
            String image = arguments.getString("image");
            String name = arguments.getString("name");
            String intro = arguments.getString("intro");
            String[] list = arguments.getStringArray("list");

            detailPlayer = root.findViewById(R.id.detail_player);

            //增加封面
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(root).load(NetUtils.BASE_URL +image).into(imageView);

            //增加title
            detailPlayer.getTitleTextView().setVisibility(View.GONE);
            detailPlayer.getBackButton().setVisibility(View.GONE);

            //外部辅助的旋转，帮助全屏
            orientationUtils = new OrientationUtils(getActivity(), detailPlayer);
            //初始化不打开外部的旋转
            orientationUtils.setEnable(false);

            gsyVideoOption = new GSYVideoOptionBuilder();
            gsyVideoOption.setThumbImageView(imageView)
                    .setIsTouchWiget(true)
                    .setRotateViewAuto(false)
                    .setLockLand(false)
                    .setAutoFullWithSize(false)
                    .setShowFullAnimation(false)
                    .setNeedLockFull(true)
                    .setUrl(url)
                    .setCacheWithPlay(false)
                    .setVideoTitle(name)
                    ///不需要旋转
                    .setNeedOrientationUtils(false)
                    .setVideoAllCallBack(new GSYSampleCallBack() {
                        @Override
                        public void onPrepared(String url, Object... objects) {
                            super.onPrepared(url, objects);
                            //开始播放了才能旋转和全屏
                            orientationUtils.setEnable(detailPlayer.isRotateWithSystem());
                            isPlay = true;
                        }

                        @Override
                        public void onQuitFullscreen(String url, Object... objects) {
                            super.onQuitFullscreen(url, objects);
                            // ------- ！！！如果不需要旋转屏幕，可以不调用！！！-------
                            // 不需要屏幕旋转，还需要设置 setNeedOrientationUtils(false)
                            if (orientationUtils != null) {
                                orientationUtils.backToProtVideo();
                            }
                        }
                    }).setLockClickListener(new LockClickListener() {
                        @Override
                        public void onClick(View view, boolean lock) {
                            if (orientationUtils != null) {
                                //配合下方的onConfigurationChanged
                                orientationUtils.setEnable(!lock);
                            }
                        }
                    }).build(detailPlayer);

            detailPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //直接横屏
                    // ------- ！！！如果不需要旋转屏幕，可以不调用！！！-------
                    // 不需要屏幕旋转，还需要设置 setNeedOrientationUtils(false)
                    orientationUtils.resolveByClick();
                    //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                    detailPlayer.startWindowFullscreen(getActivity(), true, true);
                }
            });

            List<Fragment> fragmentList = new ArrayList<>();
            fragmentList.add(new VideoIntroFragment(intro));
            fragmentList.add(new VideoListFragment(list,this));
            TabLayout tableLayout = root.findViewById(R.id.tabLayout);
            ViewPager2 viewPager2 = root.findViewById(R.id.viewPager2);
            viewPager2.setAdapter(new FragmentStateAdapter(this) {
                @NonNull
                @Override
                public Fragment createFragment(int position) {
                    return fragmentList.get(position);
                }

                @Override
                public int getItemCount() {
                    return fragmentList.size();
                }
            });
            TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tableLayout, viewPager2,
                    (tab, position) -> {
                        if (position == 0) {
                            tab.setText("视频简介");
                        } else {
                            tab.setText("视频列表");
                        }
                    });
            tabLayoutMediator.attach();
        }
        return root;
    }
    @Override
    public void onPause() {
        detailPlayer.getCurrentPlayer().onVideoPause();
        super.onPause();
        isPause = true;
    }

    @Override
    public void onResume() {
        detailPlayer.getCurrentPlayer().onVideoResume(false);
        super.onResume();
        isPause = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isPlay) {
            detailPlayer.getCurrentPlayer().release();
        }
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }


    /**
     * orientationUtils 和  detailPlayer.onConfigurationChanged 方法是用于触发屏幕旋转的
     */
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (isPlay && !isPause) {
            detailPlayer.onConfigurationChanged(getActivity(), newConfig, orientationUtils,
                    true, true);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction()==KeyEvent.ACTION_DOWN&&keyCode==KeyEvent.KEYCODE_BACK){
            if (orientationUtils!=null){
                orientationUtils.backToProtVideo();
            }
            if (GSYVideoManager.backFromWindowFull(getActivity())){
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
    public void playNewUrl(String url){
        gsyVideoOption.setUrl(url).build(detailPlayer);
        detailPlayer.startPlayLogic();
    }
}