package com.yctc.xuebaconnect;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.yctc.dao.Question;
import com.yctc.util.ReadQuestionsUtil;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class XuebaConnectActivity extends Activity implements OnClickListener {

    public Button btn_start, btn_quit;
    // ÃÌº”±≥æ∞“Ù¿÷
    public MediaPlayer mMediaPlayer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btn_start = (Button) findViewById(R.id.btn_start);
        btn_quit = (Button) findViewById(R.id.btn_quit);
        btn_quit.setOnClickListener(this);
        btn_start.setOnClickListener(this);
        
        try {
            InputStream is = this.getResources().getAssets().open("questions.xml");
            ArrayList<Question> quetions = ReadQuestionsUtil.readXML(is);
            
            for(Question qs : quetions) {
                Map<String, String> items = qs.getItem();
                Set<String> key= items.keySet();
                Iterator<String> it = key.iterator();
                while(it.hasNext()){
                    String left = it.next();
                    String right = items.get(left);
                    
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
     

    }

    // ÃÌº”±≥æ∞“Ù¿÷
    private void playBGMusic() {

        mMediaPlayer = MediaPlayer.create(this, R.raw.backgroundmusic);
        try {
            mMediaPlayer.prepare();
        } catch (IllegalStateException e) {
        } catch (IOException e) {
        }
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();
        mMediaPlayer.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                
            }
        });
    }

    // Õ£÷π±≥æ∞“Ù¿÷
    private void stopBGMusic() {
        mMediaPlayer.stop();
    }

    public void onClick(View v) {
        int id = v.getId();

        // µ„ª˜ø™ º∞¥≈• ¬º˛
        if (id == btn_quit.getId()) {
            Intent intent = new Intent();
            intent.setClass(XuebaConnectActivity.this, Select.class);
            startActivity(intent);

            // ≤•∑≈“Ù¿÷
            playBGMusic();
        } else if (id == btn_start.getId()) {
            // ActivityΩ· ¯
            XuebaConnectActivity.this.finish();
            // Õ£÷π≤•∑≈“Ù¿÷
            stopBGMusic();
        }

    }
}