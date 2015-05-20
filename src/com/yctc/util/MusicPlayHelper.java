package com.yctc.util;

import java.io.IOException;

import com.yctc.xuebaconnect.R;

import android.content.Context;
import android.media.MediaPlayer;

public class MusicPlayHelper {
    
    private static MusicPlayHelper helper;
    
    private MediaPlayer mPlayer ;
    private Context mContext;
    
    private MusicPlayHelper(Context context) {
        this.mContext = context;
        mPlayer = MediaPlayer.create(mContext, R.raw.backgroundmusic);
        mPlayer.setLooping(true);
    };
    
    public static synchronized MusicPlayHelper getInstance (Context context) {
        
        if(helper == null) {
            helper = new MusicPlayHelper(context);
        }
        return helper;
    }
    
    public void playBackGroundMusic() {
        try {
            mPlayer.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mPlayer.start();
    }
    
    public void stopMusic() {
        mPlayer.stop();
    }
    
    public void puseMusic() {
        mPlayer.pause();
    }
    

}
