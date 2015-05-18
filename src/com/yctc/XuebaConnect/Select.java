package com.yctc.XuebaConnect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Select extends Activity implements OnClickListener{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select);
		
		
	}
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_his:
			Intent intent = new  Intent();
			intent.setClass(Select.this, GameView.class);
			startActivity(intent);
			
			break;
			
		case R.id.btn_geo:
			
			break;
			
		case R.id.btn_bio:
			
			break;

		default:
			break;
		}
	}
}
