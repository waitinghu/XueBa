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

	//����һ����
	Point point = null;
	
	//����һ�����ڴ�����ӵ�ļ���
	private List<Point> points = new ArrayList<Point>();
	//TextView����
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
		
		//��ʼ��ltextView��rtextViewΪ����Ϊ6������
		TextView[][] ltextView = new TextView[6][2];
		TextView[][] rtextView = new TextView[6][2];
		
		//��������Integer���飬���ڴ��textView��
		Integer[] int1 = {R.id.txt1,R.id.txt2,R.id.txt3,R.id.txt4,R.id.txt5,R.id.txt6};
		Integer[] int2 = {R.id.txt7,R.id.txt8,R.id.txt9,R.id.txt10,R.id.txt11,R.id.txt12};
		
		//��Integer�����Ԫ�طŵ�ltextView��rtextView������
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
		
		//��������textView�����˳��
		upset(ltextView,ltextView1);
		upset(rtextView,rtextView1);
		
		for (int i = 0; i < 6; i++) {
			getPosition(ltextView1[i][2]);
			getPosition(rtextView1[i][2]);
		}
	}
	
	//��ȡTextView������
	private void getPosition(final TextView tv){
		
		tv.setClickable(true);
		tv.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//����һ���������ڴ�ŵ������
				int[] location = new int[2];
				tv.getLocationOnScreen(location);
				
				point = new Point(location[0],location[0]);
				
				points.add(point);
			}
		});
	}
	
	
	//����textView�����˳��
	private void upset(TextView[][] ltextView1, TextView[][] textView2) {
		//������ת���ɼ���
		List<TextView[]> list = Arrays.asList(ltextView1);
		//���Ҽ��ϵ�˳��
		Collections.shuffle(list);
		//������ת��Ϊ����textView1
		list.toArray(textView2);
	}
	//�ж�����TextView�Ƿ�ƥ��
	protected Boolean isMatching(TextView textView1,TextView textView2){
			for (int i = 0; i < ltextView.length; i++) {
				if (ltextView1[i][1] == rtextView1[i][1]) {
					return true;
				}
			}	
			return false;
		}
	
	
}
