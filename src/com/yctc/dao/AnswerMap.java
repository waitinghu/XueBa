package com.yctc.dao;

import java.io.Serializable;
import java.util.Map;

public class AnswerMap implements Serializable{
    private static final long serialVersionUID = 1L;
    Map<String, String> answer ;

    public Map<String, String> getAnswer() {
        return answer;
    }

    public void setAnswer(Map<String, String> answer) {
        this.answer = answer;
    }
}
