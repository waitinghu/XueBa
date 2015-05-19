package com.yctc.dao;

import java.util.Map;

public class Question {
    private String subject;
    private Map<String, String> item;
    private boolean isReaded;
    
    public boolean isReaded() {
        return isReaded;
    }


    public void setReaded(boolean isReaded) {
        this.isReaded = isReaded;
    }

    public String getSubject() {
        return subject;
    }


    public Map<String, String> getItem() {
        return item;
    }


    public void setItem(Map<String, String> item) {
        this.item = item;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
