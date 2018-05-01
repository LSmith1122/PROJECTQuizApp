package com.example.android.projectquizapp;

import java.util.ArrayList;

public class Question {
    private int tagNumber;
    private String questionString;
    private String correctAnswerString;
    private ArrayList<Answer> answerList;
    private ArrayList<String> correctAnswerList;

    Question(int tag, String question, String answer, String correctAnswer, Class answerSelectionType) {
        tagNumber = tag;
        questionString = question;
        correctAnswerString = correctAnswer;

        compileAnswers(answer, answerSelectionType);
        compileCorrectAnswers(correctAnswer);
    }

    Question(String tag, String question, String answer, String correctAnswer, String answerSelectionType, int imgResourceID) {

    }

    public int getTag() {
        return tagNumber;
    }

    public String getQuestion() {
        return questionString;
    }

    public ArrayList<String> getCorrectAnswers() {
        return correctAnswerList;
    }

    private void compileCorrectAnswers(String a) {
        String[] list = a.split(",");
        for (String answerString : list) {
            correctAnswerList.add(answerString);
        }
    }

    private void compileAnswers(String a, Class classType) {
        String[] list = a.split(",");
        for (String answerString : list) {
            answerList.add(new Answer(classType, answerString));
        }
    }
}