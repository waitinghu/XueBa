package com.yctc.xuebaconnect;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.view.View;
import android.widget.TextView;

public class DrawView extends View {
    private Paint mGesturePaint = new Paint();
    private Path mPath = new Path();
    
    Point start,end;

    public DrawView(Context context,Point start,Point end) {
        super(context);

        this.start = start;
        this.end = end;
        // 设置画笔的锯齿效果。 true是去除
        mGesturePaint.setAntiAlias(true);
        mGesturePaint.setStyle(Style.STROKE);
        // 画布尺寸
        mGesturePaint.setStrokeWidth(5);
        // 颜色
        mGesturePaint.setColor(Color.RED);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawPath(mPath, mGesturePaint);
        canvas.drawLine(start.x, start.y, end.x, end.y, mGesturePaint);
    }

    // 在两个TextView之间画线
    public void drawLine(TextView tv1, TextView tv2) {
        tv1.setClickable(true);
        int[] location1 = new int[2];
        tv1.getLocationOnScreen(location1);

        mPath.moveTo(location1[0], location1[1]);

        tv2.setClickable(true);
        int[] location2 = new int[2];
        tv2.getLocationOnScreen(location2);
        mPath.lineTo(location2[0], location2[1]);
    }

}
