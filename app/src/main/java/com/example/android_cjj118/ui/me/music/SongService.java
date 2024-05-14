package com.example.android_cjj118.ui.me.music;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;

public class SongService extends Service {
    private final MediaPlayer mediaPlayer = new MediaPlayer();
    private int index;
    private boolean isRegister;
    private final MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();
    class MyBroadcastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            int command = intent.getIntExtra("command",0);
            switch (command){
                case 1:
                    index--;
                    if(index <0){
                        index=MyData.getData().size()-1;
                    }
                    playIndex();
                    sendIndex();
                    break;
                case 2:
                    if(mediaPlayer.isPlaying()){
                        mediaPlayer.pause();
                    }else{
                        mediaPlayer.start();
                    }
                    break;
                case 3:
                    index++;
                    if(index>MyData.getData().size()-1){
                        index=0;
                    }
                    playIndex();
                    sendIndex();
                    break;
            }
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        index = intent.getIntExtra("index",0);
        playIndex();
        if(!isRegister){
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(".ui.music.MusicFragment.Broadcast");
            registerReceiver(myBroadcastReceiver,intentFilter);
            isRegister=true;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void playIndex(){
        try {
            mediaPlayer.reset();
            Uri uri = MyData.getData().get(index).getUri();
            Log.d("SongService", "播放URI：" + uri.toString());
            mediaPlayer.setDataSource(this, uri);
            System.out.println(uri);
            mediaPlayer.prepare();
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(mp -> {
                index++;
                if(index == MyData.getData().size()){
                    index = 0;
                }
                playIndex();
                sendIndex();
            });
        } catch (IOException e) {
            Log.e("SongService", "准备媒体播放器时出错：" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void sendIndex(){
        Intent intent = new Intent();
        intent.setAction(".ui.music.SongService.Broadcast");
        intent.putExtra("index",index);
        sendBroadcast(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(isRegister){
            unregisterReceiver(myBroadcastReceiver);
            isRegister = false;
        }
    }
}
