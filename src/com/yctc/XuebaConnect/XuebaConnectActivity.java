package com.yctc.XuebaConnect;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class XuebaConnectActivity extends Activity {
	
	private Button btn_start,btn_quit;
	//添加背景音乐
    private MediaPlayer mMediaPlayer;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_quit = (Button) findViewById(R.id.btn_quit);
        
        //点击开始按钮事件
        btn_start.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(XuebaConnectActivity.this, Select.class);
				startActivity(intent);
				
				//播放音乐
				playBGMusic();
			}
		});
        //点击退出按钮事件
        btn_quit.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Activity结束
				XuebaConnectActivity.this.finish();
				//停止播放音乐
				stopBGMusic();
			}
		});
    }
	
	//添加背景音乐
		private void playBGMusic() {
			
			mMediaPlayer = MediaPlayer.create(this, R.raw.backgroundmusic);
	    	try{
	    		mMediaPlayer.prepare();
	    	}catch(IllegalStateException e){
	    	}catch(IOException e){
	    	}
	    	mMediaPlayer.setLooping(true);
	    	mMediaPlayer.start();
	    	mMediaPlayer.setOnCompletionListener(new OnCompletionListener(){
	    		public void onCompletion(MediaPlayer mp){
	    		}});
		}
		//停止背景音乐
		private void stopBGMusic() {
			mMediaPlayer.stop();
		}
}