package com.example.android_cjj118.ui.me.music;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.android_cjj118.R;
import com.example.android_cjj118.base.BaseFragment2;


public class SongsFragment extends BaseFragment2 {
    private TextView textView;
    private TextView textView2;
    private ImageView imageView;
    private ImageView imageView3;
    private boolean isPlay;
    private boolean isRegister;
    private final MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();
    private Intent intent;

    class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int index = intent.getIntExtra("index", 0);
            // 使用SongsViewModel来获取歌曲数据
            SongBean song = SongsViewModel.getData().get(index);
            textView.setText(song.getName());
            textView2.setText(song.getAuthor());
            imageView.setImageResource(song.getImageId());
            isPlay = true;
            imageView3.setImageResource(R.drawable.stop);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_music, container, false);

        textView = rootView.findViewById(R.id.textView);
        textView2 = rootView.findViewById(R.id.textView2);
        imageView = rootView.findViewById(R.id.imageView);
        imageView3 = rootView.findViewById(R.id.imageView3);
        imageView3.setOnClickListener(this::click);
        rootView.findViewById(R.id.imageView2).setOnClickListener(this::click);
        rootView.findViewById(R.id.imageView4).setOnClickListener(this::click);
        super.onResume();
        intent = new Intent(requireActivity(), SongsService.class);
        requireActivity().startService(intent);
        isPlay = true;

        if (!isRegister) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.example.android_cjj118.ui.me.music.SongsService.Broadcast");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requireActivity().registerReceiver(myBroadcastReceiver, intentFilter, Context.RECEIVER_EXPORTED);
            }else {
                requireActivity().registerReceiver(myBroadcastReceiver, intentFilter);
            }
            isRegister = true;
        }
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (intent != null) {
            requireActivity().stopService(intent);
        }
        if (isRegister) {
            requireActivity().unregisterReceiver(myBroadcastReceiver);
            isRegister = false;
        }
    }

    private void click(View view) {
        Intent intent = new Intent();
        intent.setAction("com.example.android_cjj118.ui.me.music.SongsFragment.Broadcast");
        int viewId = view.getId();
        Log.e("SongsService", "command1: " + viewId);
        if (viewId == R.id.imageView2) {
            intent.putExtra("command", 1);
        } else if (viewId == R.id.imageView3) {
            if (isPlay) {
                isPlay = false;
                imageView3.setImageResource(R.drawable.play);
            } else {
                isPlay = true;
                imageView3.setImageResource(R.drawable.stop);
            }
            intent.putExtra("command", 2);
        } else if (viewId == R.id.imageView4) {
            intent.putExtra("command", 3);
        }
        requireActivity().sendBroadcast(intent);
    }

}