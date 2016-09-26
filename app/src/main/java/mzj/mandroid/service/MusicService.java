package mzj.mandroid.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import mzj.mandroid.R;
import mzj.mandroid.base.Config;

/**
 * Created by 振坚 on 2016/8/1.
 */
public class MusicService extends Service {
    private MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    //Service启动时回调该方法
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (player!=null){
            //监听音频播放完的代码，实现音频的自动循环播放
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer arg0) {
                    player.start();
                    player.setLooping(true);
                }
            });
        }

        int MusicType = intent.getIntExtra("action".toUpperCase(),Config.STOP);
        if (MusicType== Config.START) {
            start();
        } else if (MusicType== Config.STOP) {
            stops();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void start() {
        if (player == null) {
            player = MediaPlayer.create(this, R.raw.asa);
        }
        //监听音频播放完的代码，实现音频的自动循环播放
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer arg0) {
                player.start();
                player.setLooping(true);
            }
        });
        player.start();
    }
    //停止
    private void stops() {
        if (player != null) {
            player.stop();
            player.reset();
            player.release();
            player = null;
        }
    }
    //暂停
    private void pause() {
        if (player != null && player.isPlaying()) {
            player.pause();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        stops();
    }
}
