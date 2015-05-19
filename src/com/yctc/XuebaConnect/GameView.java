package com.yctc.xuebaconnect;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yctc.dao.Question;
import com.yctc.util.ReadQuestionsUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameView extends Activity implements OnClickListener {

    // 定义一个点
    Point point = null;
    // 创建一个用于存放连接点的集合
    private List<Point> points = new ArrayList<Point>();
    // TextView数组
    TextView[][] ltextView, rtextView, ltextView1, rtextView1;

    private String mSubject;
    private ArrayList<Question> questions;
    private Question question;
    private LinearLayout mLeftView, mRightView;
    private LinearLayout root;

    private ArrayList<String> mLeft = new ArrayList<String>();
    private ArrayList<String> mRight = new ArrayList<String>();

    boolean hasLeftPressed;
    boolean hasRightPressed;

    TextView leftSelect;
    TextView rightSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view);
        root = (LinearLayout) findViewById(R.id.root);
        Intent intent = getIntent();
        mSubject = intent.getStringExtra(Select.KEY_SUBJECT_KIND);
        mLeftView = (LinearLayout) findViewById(R.id.left_container);
        mRightView = (LinearLayout) findViewById(R.id.right_container);
        loadQuestion();
    }

    private void initGameDate() {
        question = ReadQuestionsUtil.getQuestionBySubject(mSubject, questions);
        question.setReaded(true);
        Map<String, String> items = question.getItem();
        Set<String> key = items.keySet();
        Iterator<String> it = key.iterator();
        while (it.hasNext()) {
            String itemKey = it.next();
            mLeft.add(itemKey);
            mRight.add(items.get(itemKey));
        }
    }

    // 定义两个Integer数组，用于存放textView的
    int[] leftID = { R.id.txt1, R.id.txt2, R.id.txt3, R.id.txt4, R.id.txt5,
            R.id.txt6 };
    int[] rightID = { R.id.txt7, R.id.txt8, R.id.txt9, R.id.txt10, R.id.txt11,
            R.id.txt12 };

    private void initGame() {

        Collections.shuffle(mLeft);
        Collections.shuffle(mRight);

        for (int i = 0; i < leftID.length; i++) {
            TextView viewLeft = (TextView) mLeftView.findViewById(leftID[i]);
            TextView viewRight = (TextView) mRightView.findViewById(rightID[i]);
            viewLeft.setText(mLeft.get(i));
            viewRight.setText(mRight.get(i));
            viewLeft.setOnClickListener(this);
            viewRight.setOnClickListener(this);

        }
    }

    public void onClick(View v) {
        int id = v.getId();

        for (int i = 0; i < leftID.length; i++) {
            if (id == leftID[i]) {
                v.setBackgroundColor(0x88888);
                hasLeftPressed = true;
                leftSelect = (TextView) v;
            }
        }

        for (int i = 0; i < rightID.length; i++) {
            if (id == rightID[i]) {
                v.setBackgroundColor(Color.BLUE);
                hasRightPressed = true;
                rightSelect = (TextView) v;
            }
        }

        if (hasLeftPressed && hasRightPressed) {
            Point left = getPosition(leftSelect);
            Point right = getPosition(rightSelect);
            drawLine(left, right);
            hasLeftPressed = false;
            hasRightPressed = false;
            leftSelect = null;
            rightSelect = null;
        }

    }

    private void initGame1() {

        Resources res = getResources();
        String[] left = res.getStringArray(R.array.leftmodule);
        String[] right = res.getStringArray(R.array.rightmodule);

        // 初始化ltextView，rtextView为长度为6的数组
        TextView[][] ltextView = new TextView[6][2];
        TextView[][] rtextView = new TextView[6][2];

        // 定义两个Integer数组，用于存放textView的
        Integer[] int1 = { R.id.txt1, R.id.txt2, R.id.txt3, R.id.txt4,
                R.id.txt5, R.id.txt6 };
        Integer[] int2 = { R.id.txt7, R.id.txt8, R.id.txt9, R.id.txt10,
                R.id.txt11, R.id.txt12 };

        // 把Integer数组的元素放到ltextView，rtextView数组中
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

        // 打乱两个textView数组的顺序
        upset(ltextView, ltextView1);
        upset(rtextView, rtextView1);

        for (int i = 0; i < 6; i++) {
            getPosition(ltextView1[i][2]);
            getPosition(rtextView1[i][2]);
        }
    }

    // 获取TextView的坐标
    private Point getPosition(final TextView tv) {
        // TODO Auto-generated method stub
        // 定义一个数组用于存放点的坐标
        int[] location = new int[2];
        tv.getLocationOnScreen(location);

        Point point = new Point(location[0], location[1]);

        return point;
    }

    // 打乱textView数组的顺序
    private void upset(TextView[][] ltextView1, TextView[][] textView2) {
        // 将数组转化成集合
        List<TextView[]> list = Arrays.asList(ltextView1);
        // 打乱集合的顺序
        Collections.shuffle(list);
        // 将集合转换为数组textView1
        list.toArray(textView2);
    }

    // 判断两个TextView是否匹配
    protected Boolean isMatching(TextView textView1, TextView textView2) {
        for (int i = 0; i < ltextView.length; i++) {
            if (ltextView1[i][1] == rtextView1[i][1]) {
                return true;
            }
        }
        return false;
    }

    // 两个点之间划线
    public void drawLine(Point start, Point end) {
        DrawView view = new DrawView(this, start, end);
        view.invalidate();
        root.addView(view);
    }

    public class LoadQuestionTask extends
            AsyncTask<Void, Integer, ArrayList<Question>> {

        private Context mContext;

        public LoadQuestionTask(Context context) {
            this.mContext = context;
        }

        @Override
        protected void onPreExecute() {
            Toast.makeText(mContext, "初始化题库中...", Toast.LENGTH_SHORT).show();
            super.onPreExecute();
        }

        @Override
        protected ArrayList<Question> doInBackground(Void... params) {
            ArrayList<Question> quetiongs = null;
            try {
                InputStream is = mContext.getResources().getAssets()
                        .open("questions.xml");
                quetiongs = ReadQuestionsUtil.readXML(is);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return quetiongs;
        }

        @Override
        protected void onPostExecute(ArrayList<Question> result) {
            questions = result;
            initGameDate();
            initGame();
            super.onPostExecute(result);
        }
    }

    private void loadQuestion() {
        LoadQuestionTask task = new LoadQuestionTask(getApplicationContext());
        task.execute();
    }
}
