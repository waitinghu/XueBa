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
	
	//��Ϸ��ͼ
	private GameView gameView;
	//���尴ť
	private Button btn_pause,btn_contin;
	//����һ��TextView������ʾ�÷�
	private TextView score;
	//�÷�
	private int num;
	//��Ϸ���������ĶԻ���
	private AlertDialog.Builder endDialog;
	//��Ϸ��ͣ�����ĶԻ���
	private AlertDialog pauseDialog;
	// ��¼�Ƿ�����Ϸ״̬
	private boolean isPlaying;
	// �񶯴�����
	private Vibrator vibrator;
	// ��¼�Ѿ�ѡ�еķ���
	private TextView selected = null;
	//��ǰѡ��ķ���
	private TextView current = null;
	//ý�岥����
	private MediaPlayer player = null;
	//��Ч������
	private SoundPool sound = null;
	
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
			
		setContentView(R.layout.view);
		//��Ϸ��ʼ��
		init();
	}

	private void init() {
		//��ȡ��Դ
		btn_pause = (Button) findViewById(R.id.btn_pause);
		btn_contin = (Button) findViewById(R.id.btn_contin);
		score = (TextView) findViewById(R.id.score);
		
		player = MediaPlayer.create(this, R.raw.backgroundmusic);
		player.setLooping(true);
		player.start();
		
		//��ȡ����
		vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
		
		btn_pause.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pauseDialog.show();
				player.pause();
			}
		});
		
		pauseDialog = new AlertDialog.Builder(getBaseContext()).setTitle("").setMessage("��Ϸ��ͣ").setPositiveButton("������Ϸ",new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				startGame();
			}
	
		} ).show();
	}

	//��Ϸ��ͣ
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
		// ���������Ϸ״̬��
		if (isPlaying){
			player.start();
		}		
		super.onResume();
	}
	
	private void startGame() {
		//����÷�
		clearScore();
		//��Ϸ״̬��Ϊtrue
		isPlaying = true;
		// ��ѡ�е�ģ����Ϊnull
		this.selected = null;
			}
	//��Ϸ�����¼�������
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

	//��ָ������Ļʱ����
	 private void touchMove(MotionEvent event) {
		
	}
	//������һ����ʱ����
	private void touchDown(MotionEvent event) {
		
	}

	//��յ÷�
    public void clearScore(){
    	num = 0;
    	showScore();
    }
    //��ʾ�÷�
    public void showScore(){
    	score.setText(num+"");
    }
    //�ӷ���
    public void addScore(int n){
    	num += n;
    	showScore();
    }

	
}
