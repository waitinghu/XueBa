package com.yctc.xuebaconnect;

import java.util.ArrayList;
import java.util.Map;

import com.yctc.dao.AnswerMap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ViewAnswerActivity extends Activity{
    
    
    private ArrayList<String> mLeft = new ArrayList<String>();
    private ArrayList<String> mRight = new ArrayList<String>();
    
    // 定义两个Integer数组，用于存放textView的
    int[] leftID = { R.id.atxt1, R.id.atxt2, R.id.atxt3, R.id.atxt4, R.id.atxt5,
            R.id.atxt6 };
    int[] rightID = { R.id.atxt7, R.id.atxt8, R.id.atxt9, R.id.atxt10, R.id.atxt11,
            R.id.atxt12 };
    
    ImageButton button;
    Map<String, String> answer;
    RelativeLayout root;
    String mSubject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_answer);
        Intent intent = getIntent();
        root = (RelativeLayout) findViewById(R.id.root);
        mLeft = intent.getStringArrayListExtra("left");
        mRight = intent.getStringArrayListExtra("right");
        mSubject = intent.getStringExtra("subject");
        AnswerMap answermap = (AnswerMap) intent.getSerializableExtra("answser");
        answer = answermap.getAnswer();
        ImageButton nextGame = (ImageButton) findViewById(R.id.next_game);
        
        nextGame.setOnClickListener(new OnClickListener() {
            
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GameView.class);
                intent.putExtra(Select.KEY_SUBJECT_KIND, mSubject);
                startActivity(intent);
                ViewAnswerActivity.this.finish();
            }
        });
        
        for (int i = 0; i < leftID.length; i++) {
            TextView viewLeft = (TextView)findViewById(leftID[i]);
            TextView viewRight = (TextView)findViewById(rightID[i]);
            viewLeft.setText(mLeft.get(i));
            viewRight.setText(mRight.get(i));
        }
        
        
    }
    
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        for (int j = 0; j < mLeft.size(); j++) {
            String value = answer.get(mLeft.get(j));
            for (int i = 0; i < mRight.size(); i++) {
                if(mRight.get(i).equals(value)){
                    TextView left = (TextView) findViewById(leftID[j]);
                    TextView right = (TextView) findViewById(rightID[i]);
                    View line = drawLine(getPosition(left, true), getPosition(right, false));
                    root.addView(line);
                }
            }
        }
    }
    
    // 两个点之间划线
    public DrawView drawLine(Point start, Point end) {
        DrawView view = new DrawView(this, start, end);
        return view;
    }
    
    public Point getPosition(TextView tv, boolean isLift) {

        int[] location = new int[2];
        tv.getLocationInWindow(location);

        int contentTop = getWindow().findViewById(Window.ID_ANDROID_CONTENT)
                .getTop();
        location[1] = location[1] - contentTop - tv.getHeight();

        if (isLift) {
            location[0] += tv.getWidth();
        }

        Point point = new Point(location[0], location[1] + 10);

        return point;
    }

}
