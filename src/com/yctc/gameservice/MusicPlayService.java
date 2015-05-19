package com.yctc.gameservice;

import java.io.IOException;

import com.yctc.xuebaconnect.R;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicPlayService extends Service{
    
    MediaPlayer mPlayer ;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        
        mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.backgroundmusic);
        
        try {
            mPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        mPlayer.setLooping(true);
        mPlayer.start();
        
        return super.onStartCommand(intent, flags, startId);
    }

}
