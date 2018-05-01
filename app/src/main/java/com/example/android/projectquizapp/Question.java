package com.example.android.projectquizapp;

import java.util.ArrayList;

public class Question {
    private int tagNumber;
    private String questionString;
    private ArrayList<Answer> answerList;

    Question(int tag, String question, String answer, String correctAnswer, Class answerSelectionType) {
        tagNumber = tag;
        questionString = question;

        compileAnswers(answer, answerSelectionType);

    }

    Question(String tag, String question, String answer, String correctAnswer, String answerSelectionType, int imgResourceID) {

    }

    public int getTag() {
        return tagNumber;
    }

    public String getQuestion() {
        return questionString;
    }

    private void compileAnswers(String a, Class classType) {
        // TODO: Separate the String of answers (by ",") into individual String answers, and then place them into an ArrayList
        String[] list = a.split(",");
        for (String answerString : list) {
            answerList.add(new Answer(classType, answerString));
        }
    }
}
