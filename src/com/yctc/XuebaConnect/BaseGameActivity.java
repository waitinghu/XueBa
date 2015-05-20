package com.yctc.xuebaconnect;

import com.yctc.dao.Question;
import com.yctc.util.ReadQuestionsUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

public abstract class BaseGameActivity extends Activity {

    public Question question;
    public String mSubject;
    
    public abstract void initGame();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        mSubject = intent.getStringExtra(Select.KEY_SUBJECT_KIND);
        loadQuestion();
    }
    
    
    public class LoadQuestionTask extends AsyncTask<Void, Integer, Question> {

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
        protected Question doInBackground(Void... params) {
            Question question = null;
            try {
                question = ReadQuestionsUtil.getQuestionBySubject(mSubject,
                        mContext);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return question;
        }

        @Override
        protected void onPostExecute(Question result) {
            super.onPostExecute(result);
            question = result;
            initGame();
        }
    }
    
    public void loadQuestion() {
        LoadQuestionTask task = new LoadQuestionTask(getApplicationContext());
        task.execute();
    }
    
    // 两个点之间划线
    public DrawView drawLine(Point start, Point end) {
        DrawView view = new DrawView(this, start, end);
        return view;
    }

}
