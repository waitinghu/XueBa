package com.yctc.XuebaConnect;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Link extends Activity {
	
	//游戏视图
	private GameView gameView;
	//定义按钮
	private Button btn_pause,btn_contin;
	//定义一个TextView用于显示得分
	private TextView score;
	//得分
	private int num;
	//游戏结束弹出的对话框
	private AlertDialog.Builder endDialog;
	//游戏暂停弹出的对话框
	private AlertDialog pauseDialog;
	// 记录是否处于游戏状态
	private boolean isPlaying;
	// 振动处理类
	private Vibrator vibrator;
	// 记录已经选中的方块
	private TextView selected = null;
	//当前选择的方块
	private TextView current = null;
	//媒体播放器
	private MediaPlayer player = null;
	//音效播放器
	private SoundPool sound = null;
	
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
			
		setContentView(R.layout.view);
		//游戏初始化
		init();
	}

	private void init() {
		//获取资源
		btn_pause = (Button) findViewById(R.id.btn_pause);
		btn_contin = (Button) findViewById(R.id.btn_contin);
		score = (TextView) findViewById(R.id.score);
		
		player = MediaPlayer.create(this, R.raw.backgroundmusic);
		player.setLooping(true);
		player.start();
		
		//获取震动器
		vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
		
		btn_pause.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pauseDialog.show();
				player.pause();
			}
		});
		
		pauseDialog = new AlertDialog.Builder(getBaseContext()).setTitle("").setMessage("游戏暂停").setPositiveButton("继续游戏",new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				startGame();
			}
	
		} ).show();
	}

	//游戏暂停
	@Override
	protected void onPause() {
		if (player.isPlaying())
		{
			player.pause();
		}
		
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		// 如果处于游戏状态中
		if (isPlaying){
			player.start();
		}		
		super.onResume();
	}
	
	private void startGame() {
		//清除得分
		clearScore();
		//游戏状态设为true
		isPlaying = true;
		// 将选中的模块设为null
		this.selected = null;
			}
	//游戏区域事件侦听器
	public boolean onTouchEvent(MotionEvent event){
		switch (event.getAction()){
		case MotionEvent.ACTION_DOWN:
			touchDown(event);
			break;
		case MotionEvent.ACTION_MOVE:
			touchMove(event);
			break;
		}
		return true;
	}

	//手指点下屏幕时调用
	 private void touchMove(MotionEvent event) {
		
	}
	//点下另一个点时调用
	private void touchDown(MotionEvent event) {
		
	}

	//清空得分
    public void clearScore(){
    	num = 0;
    	showScore();
    }
    //显示得分
    public void showScore(){
    	score.setText(num+"");
    }
    //加分器
    public void addScore(int n){
    	num += n;
    	showScore();
    }

	
}
