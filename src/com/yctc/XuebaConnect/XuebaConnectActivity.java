package com.yctc.xuebaconnect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.yctc.util.MusicPlayHelper;

public class XuebaConnectActivity extends Activity implements OnClickListener {

    public Button btn_start, btn_quit;

    private MusicPlayHelper helper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btn_start = (Button) findViewById(R.id.btn_start);
        btn_quit = (Button) findViewById(R.id.btn_quit);
        btn_quit.setOnClickListener(this);
        btn_start.setOnClickListener(this);

        helper = MusicPlayHelper.getInstance(this);
        helper.playBackGroundMusic();
    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == btn_start.getId()) {
            Intent intent = new Intent();
            intent.setClass(XuebaConnectActivity.this, Select.class);
            startActivity(intent);
        } else if (id == btn_quit.getId()) {
            XuebaConnectActivity.this.finish();
        }
    }
}