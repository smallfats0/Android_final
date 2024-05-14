package com.example.android_cjj118.ui.me.music;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;



import java.io.IOException;
public class SongsService extends Service {
    private static final String TAG = "SongsService";
    private int index;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private boolean isRegister;
    private final MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();
    private Intent intent;

    class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int command = intent.getIntExtra("command", 0);
            Log.e("SongsService", "command: " + command);
            switch (command) {
                case 1:
                    index--;
                    if (index < 0) {
                        index = SongsViewModel.getData().size() - 1;
                    }
                    playIndex();
                    sendIndex();
                    break;
                case 2:
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                        Log.e("SongsService", "pause");
                    } else {
                        mediaPlayer.start();
                    }
                    break;
                case 3:
                    index++;
                    if (index > SongsViewModel.getData().size() - 1) {
                        index = 0;
                    }
                    playIndex();
                    sendIndex();
                    break;
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        index = intent.getIntExtra("index", 0);
        playIndex();
        if (!isRegister) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.example.android_cjj118.ui.me.music.SongsFragment.Broadcast");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                registerReceiver(myBroadcastReceiver, intentFilter, Context.RECEIVER_EXPORTED);
            } else {
                registerReceiver(myBroadcastReceiver, intentFilter);
            }
            isRegister = true;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void playIndex() {
        try {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.reset();
            mediaPlayer.setDataSource(this, SongsViewModel.getData().get(index).getUri());
            mediaPlayer.prepare();
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(mp -> {
                index++;
                if (index == SongsViewModel.getData().size()) {
                    index = 0;
                }
                playIndex();
                sendIndex();
            });
        } catch (IOException e) {
            Log.e(TAG, "IOException: ", e);
        }
    }

    private void sendIndex() {
        Intent intent = new Intent();
        intent.setAction("com.example.android_cjj118.ui.me.music.SongsService.Broadcast");
        intent.putExtra("index", index);
        sendBroadcast(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isRegister) {
            unregisterReceiver(myBroadcastReceiver);
            isRegister = false;
        }
        mediaPlayer.release();
    }
}

