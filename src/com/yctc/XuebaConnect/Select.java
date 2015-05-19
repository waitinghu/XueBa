package com.yctc.xuebaconnect;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Select extends Activity implements OnClickListener{
	
    public static final String KEY_SUBJECT_KIND = "com.yctc.subject";
    
    public Button mHistory;
    public Button mBiology;
    public Button mGeography;
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select);
		mHistory = (Button) findViewById(R.id.btn_his);
		mBiology = (Button) findViewById(R.id.btn_bio);
		mGeography = (Button) findViewById(R.id.btn_geo);
		mHistory.setOnClickListener(this);
		mBiology.setOnClickListener(this);
		mGeography.setOnClickListener(this);
	}
	

	
    public void onClick(View v) {
	    Intent intent = new Intent(this,GameView.class);
		switch (v.getId()) {
		case R.id.btn_his:
			intent.putExtra(KEY_SUBJECT_KIND, "history");
			break;
		case R.id.btn_geo:
		    intent.putExtra(KEY_SUBJECT_KIND, "geography");
			break;
		case R.id.btn_bio:
		    intent.putExtra(KEY_SUBJECT_KIND, "biology");
			break;
		default:
			break;
		}
        startActivity(intent);
	}
}
