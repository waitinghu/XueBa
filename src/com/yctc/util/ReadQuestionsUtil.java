package com.yctc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;




import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.yctc.dao.Question;

import android.content.Context;
import android.util.Xml;

public class ReadQuestionsUtil {

    private static XmlPullParser parser = Xml.newPullParser();
    
    private static ArrayList<Question> questions = null;
    
    public static Question getQuestionBySubject(String subject,Context context) {
        
        InputStream is = null;
        try {
            is = context.getAssets().open("questions.xml");
            questions = readXML(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        for(Question qes : questions) {

            if(qes.getSubject().equals(subject)) {
                if(qes.isReaded()) {
                    continue;
                }
                qes.setReaded(true);
                return qes;
            }
        }
        
        for(Question qes : questions) {
            if(qes.getSubject().equals(subject)) {
                qes.setReaded(true);
                return qes;
            }
        }
        return null;
    }
    

    public static ArrayList<Question> readXML(InputStream is) throws XmlPullParserException,
            IOException {
        
        if(questions != null && questions.size() != 0) {
            return questions;
        }

        parser.setInput(is, "UTF-8");

        ArrayList<Question> questions = null;
        Question question = null;
        Map<String, String> items = null;

        int eventType = parser.getEventType();// ��ʼ������һ�¼�
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
            case (XmlPullParser.START_DOCUMENT):// ������ĵ���ʼ�¼�
                questions = new ArrayList<Question>();
                break;
            case (XmlPullParser.START_TAG):// ���������ǩ��ʼ

                String tagName = parser.getName();// ��ý�������ǰԪ�ص�����
                if ("question".equals(tagName)) {
                    question = new Question();
                    question.setReaded(false);
                    question.setSubject(parser.getAttributeValue(0));
                    items = new HashMap<String, String>();
                }
                if ("item".equals(tagName) && question != null && items != null) {
                    items.put(parser.getAttributeValue(0), parser.nextText());
                }
                break;
            case (XmlPullParser.END_TAG):// ���������ǩ����

                String tagName1 = parser.getName();// ��ý�������ǰԪ�ص�����
                if ("question".equals(tagName1)) {
                    question.setItem(items);
                    questions.add(question);
                    question = null;
                }
                break;
            }

            eventType = parser.next();// ������һ���¼�����
        }

        return questions;
    }
}
