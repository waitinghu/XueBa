package com.yctc.xuebaconnect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import com.yctc.dao.AnswerMap;
import com.yctc.dao.OneConnect;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GameView extends BaseGameActivity implements OnClickListener {

    private LinearLayout mLeftView, mRightView;
    private RelativeLayout root;

    private ArrayList<String> mLeft = new ArrayList<String>();
    private ArrayList<String> mRight = new ArrayList<String>();

    private TextView leftSelect;
    private TextView rightSelect;

    private Button btn_back;

    private Vibrator vibrator;

    public static final int STATE_INIT = 0;
    public static final int STATE_SELECT = 1;

    private LinkedList<OneConnect> playResult = new LinkedList<OneConnect>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view);
        root = (RelativeLayout) findViewById(R.id.root);
        Intent intent = getIntent();
        mSubject = intent.getStringExtra(Select.KEY_SUBJECT_KIND);
        mLeftView = (LinearLayout) findViewById(R.id.left_container);
        mRightView = (LinearLayout) findViewById(R.id.right_container);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        btn_back = (Button) findViewById(R.id.btn_contin);
        btn_back.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                back();
            }
        });

    }

    // 定义两个Integer数组，用于存放textView的
    int[] leftID = { R.id.txt1, R.id.txt2, R.id.txt3, R.id.txt4, R.id.txt5,
            R.id.txt6 };
    int[] rightID = { R.id.txt7, R.id.txt8, R.id.txt9, R.id.txt10, R.id.txt11,
            R.id.txt12 };

    public void initGame() {

        question.setReaded(true);
        Map<String, String> items = question.getItem();
        Set<String> key = items.keySet();
        Iterator<String> it = key.iterator();
        while (it.hasNext()) {
            String itemKey = it.next();
            mLeft.add(itemKey);
            mRight.add(items.get(itemKey));
        }

        Collections.shuffle(mLeft);
        Collections.shuffle(mRight);

        for (int i = 0; i < leftID.length; i++) {
            TextView viewLeft = (TextView) mLeftView.findViewById(leftID[i]);
            TextView viewRight = (TextView) mRightView.findViewById(rightID[i]);
            viewLeft.setTag(STATE_INIT);
            viewRight.setTag(STATE_INIT);
            viewLeft.setText(mLeft.get(i));
            viewRight.setText(mRight.get(i));
            viewLeft.setOnClickListener(this);
            viewRight.setOnClickListener(this);

        }
    }

    public void onClick(View v) {
        int id = v.getId();
        vibrator.vibrate(10);
        int state = (Integer) v.getTag();

        for (int i = 0; i < leftID.length; i++) {
            if (id == leftID[i]) {

                if (leftSelect != null
                        && (Integer) leftSelect.getTag() == STATE_SELECT) {
                    leftSelect.setTag(STATE_INIT);
                    leftSelect.setBackgroundResource(R.drawable.module);
                    leftSelect = null;
                }
                if (state == STATE_INIT) {
                    v.setBackgroundResource(R.drawable.selected);
                    v.setTag(STATE_SELECT);
                    leftSelect = (TextView) v;
                }
            }
        }

        for (int i = 0; i < rightID.length; i++) {
            if (id == rightID[i]) {
                if (rightSelect != null
                        && (Integer) rightSelect.getTag() == STATE_SELECT) {
                    rightSelect.setTag(STATE_INIT);
                    rightSelect.setBackgroundResource(R.drawable.module);
                    rightSelect = null;
                }
                if (state == STATE_INIT) {
                    v.setBackgroundResource(R.drawable.selected);
                    v.setTag(STATE_SELECT);
                    rightSelect = (TextView) v;
                }
            }
        }

        if (leftSelect != null && rightSelect != null) {
            Point left = getPosition(leftSelect, true);
            Point right = getPosition(rightSelect, false);
            View line = drawLine(left, right);
            root.addView(line);
            playResult.add(new OneConnect(line, new TextView[] { leftSelect,
                    rightSelect }));
            leftSelect = null;
            rightSelect = null;
            if (playResult.size() >= 6) {
                GameOver();
            }
        }
    }

    private void GameOver() {
        
        new AlertDialog.Builder(GameView.this)
                .setTitle("游戏结束")
                .setMessage("您的游戏得分是" + getScore())
                .setPositiveButton("查看答案",new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog,int which) {
                                Intent intent = new Intent(getApplicationContext(), ViewAnswerActivity.class);
                                AnswerMap answer = new AnswerMap();
                                intent.putExtra("subject", mSubject);
                                answer.setAnswer(question.getItem());
                                intent.putExtra("answser", answer);
                                intent.putStringArrayListExtra("left", mLeft);
                                intent.putStringArrayListExtra("right", mRight);
                                startActivity(intent);
                            }

                        })
                 .setNegativeButton("下一题", new DialogInterface.OnClickListener() {
                    
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), GameView.class);
                        intent.putExtra(Select.KEY_SUBJECT_KIND, mSubject);
                        startActivity(intent);
                        GameView.this.finish();
                    }
                }).show();
    }

    
    private int getScore() {
        
        
        return 50;
    }

    public void back() {

        if (playResult != null && playResult.size() > 0) {
            OneConnect connect = playResult.removeLast();
            View line = connect.getLine();
            root.removeView(line);
            TextView[] views = connect.getTextViews();
            for (int i = 0; i < views.length; i++) {
                views[i].setBackgroundResource(R.drawable.module);
                views[i].setTag(STATE_INIT);
            }
        }
    }

    private Point getPosition(TextView tv, boolean isLift) {

        int[] location = new int[2];
        tv.getLocationInWindow(location);

        int contentTop = getWindow().findViewById(Window.ID_ANDROID_CONTENT)
                .getTop();
        location[1] = location[1] - contentTop - tv.getHeight();

        if (isLift) {
            location[0] += tv.getWidth();
        }

        Point point = new Point(location[0], location[1] + 6);

        return point;
    }
}
