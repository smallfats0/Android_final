package com.example.android_cjj118.ui.me.music;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.android_cjj118.R;
import com.example.android_cjj118.base.BaseFragment2;

public class MusicFragment extends BaseFragment2 implements View.OnClickListener {

    private Intent intent;
    private boolean isPlay;
    private TextView textView;
    private TextView textView2;
    private ImageView imageView;
    private ImageView imageView3;
    private boolean isRegister;
    private final MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_music, container, false);

        intent = new Intent(requireContext(), SongService.class);
        requireContext().startService(intent);
        isPlay = true;

        if (!isRegister) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(".ui.music.SongService.Broadcast");
            requireContext().registerReceiver(myBroadcastReceiver, intentFilter);
            isRegister = true;
        }

        textView = root.findViewById(R.id.textView);
        textView2 = root.findViewById(R.id.textView2);
        imageView = root.findViewById(R.id.imageview);
        imageView3 = root.findViewById(R.id.imageview3);
        imageView3.setOnClickListener(this);
        root.findViewById(R.id.imageView2).setOnClickListener(this);
        root.findViewById(R.id.imageView4).setOnClickListener(this);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (intent != null) {
            requireContext().stopService(intent);
        }
        if (isRegister) {
            requireContext().unregisterReceiver(myBroadcastReceiver);
            isRegister = false;
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setAction(".ui.music.MusicFragment.Broadcast");
        int viewId = view.getId();
        if (viewId == R.id.imageView2) {
            intent.putExtra("command", 1);
        } else if (viewId == R.id.imageview3) {
            intent.putExtra("command", 2);
            if (isPlay) {
                isPlay = false;
                imageView3.setImageResource(R.drawable.play);
            } else {
                isPlay = true;
                imageView3.setImageResource(R.drawable.stop);
            }
        } else if (viewId == R.id.imageView4) {
            intent.putExtra("command", 3);
        }
        requireContext().sendBroadcast(intent);
    }

    private class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int index = intent.getIntExtra("index", 0);
            textView.setText(MyData.getData().get(index).getName());
            textView2.setText(MyData.getData().get(index).getAuthor());
            imageView.setImageResource(MyData.getData().get(index).getImageId());
            isPlay = true;
            imageView3.setImageResource(R.drawable.stop);
        }
    }
}
