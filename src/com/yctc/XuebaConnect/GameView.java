package com.yctc.XuebaConnect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class GameView extends Activity{

	//定义一个点
	Point point = null;
	
	//创建一个用于存放连接点的集合
	private List<Point> points = new ArrayList<Point>();
	//TextView数组
	TextView[][]  ltextView,rtextView,ltextView1,rtextView1;
	
	public Point getPoint() {
		return point;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view);
		
		initgame();
	}

	private void initgame() {
		Resources res = getResources();
		String[] left = res.getStringArray(R.array.leftmodule);
		String[] right = res.getStringArray(R.array.rightmodule);
		
		//初始化ltextView，rtextView为长度为6的数组
		TextView[][] ltextView = new TextView[6][2];
		TextView[][] rtextView = new TextView[6][2];
		
		//定义两个Integer数组，用于存放textView的
		Integer[] int1 = {R.id.txt1,R.id.txt2,R.id.txt3,R.id.txt4,R.id.txt5,R.id.txt6};
		Integer[] int2 = {R.id.txt7,R.id.txt8,R.id.txt9,R.id.txt10,R.id.txt11,R.id.txt12};
		
		//把Integer数组的元素放到ltextView，rtextView数组中
		for (int i = 0; i < int1.length; i++) {
			ltextView[i][1].setText(i);
			ltextView[i][2] = (TextView) findViewById(int1[i]);
			ltextView[i][2].setText(left[i]);
			}
		for (int j = 0; j < int2.length; j++) {
			rtextView[j][1].setText(j);
			rtextView[j][2] = (TextView) findViewById(int2[j]);
			rtextView[j][2].setText(right[j]);
			}
		
		//打乱两个textView数组的顺序
		upset(ltextView,ltextView1);
		upset(rtextView,rtextView1);
		
		for (int i = 0; i < 6; i++) {
			getPosition(ltextView1[i][2]);
			getPosition(rtextView1[i][2]);
		}
	}
	
	//获取TextView的坐标
	private void getPosition(final TextView tv){
		
		tv.setClickable(true);
		tv.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//定义一个数组用于存放点的坐标
				int[] location = new int[2];
				tv.getLocationOnScreen(location);
				
				point = new Point(location[0],location[0]);
				
				points.add(point);
			}
		});
	}
	
	
	//打乱textView数组的顺序
	private void upset(TextView[][] ltextView1, TextView[][] textView2) {
		//将数组转化成集合
		List<TextView[]> list = Arrays.asList(ltextView1);
		//打乱集合的顺序
		Collections.shuffle(list);
		//将集合转换为数组textView1
		list.toArray(textView2);
	}
	//判断两个TextView是否匹配
	protected Boolean isMatching(TextView textView1,TextView textView2){
			for (int i = 0; i < ltextView.length; i++) {
				if (ltextView1[i][1] == rtextView1[i][1]) {
					return true;
				}
			}	
			return false;
		}
	
	
}
