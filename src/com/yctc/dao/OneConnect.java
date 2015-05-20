package com.yctc.dao;

import android.view.View;
import android.widget.TextView;

public class OneConnect {
    
    private View line;
    private TextView [] TextViews ;
    public View getLine() {
        return line;
    }
    public void setLine(View line) {
        this.line = line;
    }
    public TextView[] getTextViews() {
        return TextViews;
    }
    public void setTextViews(TextView[] textViews) {
        TextViews = textViews;
    }
    public OneConnect(View line, TextView[] textViews) {
        super();
        this.line = line;
        TextViews = textViews;
    }

}
