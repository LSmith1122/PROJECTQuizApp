package com.example.android.projectquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    // Section Score Variables

    // Content Variables
    boolean q1, q2, q3, q4, q5 = false;         // determines whether or not a question has been answered
    boolean answerCorrectQ1, answerCorrectQ2, answerCorrectQ3, answerCorrectQ4, answerCorrectQ5, answerCorrectQ5_A1, answerCorrectQ5_A2, answerCorrectQ5_A3;        // determines whether the answer chosen for its respective question is correct
    boolean areAllQuestionsAnswered = false;        // determines if all questions have been answered - not necessarily correct
    int q2_a2_stat, q2_a1_stat = 0;     // current state of whether both 'correct' options are selected for Question 2
    int q1_checkedOptionsQuantity, q2_checkedOptionsQuantity, q3_checkedOptionsQuantity, q4_checkedOptionsQuantity = 0;     // quantity of objects checked within its respective group
    String q_string;        // Question 5's TextBox text (String)
    int maxScore = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkResponse(View view) {

        if (areAllQuestionsAnswered()) {         // if all questions answered...

            String finalScore = String.valueOf(tallyAnswers());
            Intent i = new Intent(this, FinalScore_Activity.class);
            Bundle bundle = new Bundle();
            bundle.putString("final score", finalScore);
            i.putExtras(bundle);
            startActivity(i);

        } else {
            Toast.makeText(getApplicationContext(), "Please answer all questions", Toast.LENGTH_SHORT).show(); // TODO: *** MANDATORY: DO NO REMOVE ***
        }
    }

    public double tallyAnswers() {
        double photography_score = 0;
        if (answerCorrectQ1) {
            photography_score++;
        }
        if (answerCorrectQ2) {
            photography_score++;
        }
        if (answerCorrectQ3) {
            photography_score++;
        }
        if (answerCorrectQ4) {
            photography_score++;
        }
        if (answerCorrectQ5()) {
            photography_score++;
        }
        double score = photography_score / maxScore * 100;
        return score;
    }

    public boolean areAllQuestionsAnswered() {

        if (q1 && q2 && q3 && q4) {
            EditText textboxS = findViewById(R.id.question_5_answer_1);
            EditText textboxL = findViewById(R.id.question_5_answer_2);
            EditText textboxR = findViewById(R.id.question_5_answer_3);
            if (answeredQ5(textboxS) && answeredQ5(textboxL) && answeredQ5(textboxR)) {
                areAllQuestionsAnswered = true;
            } else {
                Toast.makeText(getApplicationContext(), "The last question is not answered thoroughly", Toast.LENGTH_SHORT).show();
            }
        } else {            // All questions within 1-4 were NOT answered...
            Toast.makeText(getApplicationContext(), "Please answer the last question", Toast.LENGTH_SHORT).show();
        }

        return areAllQuestionsAnswered;
    }

    public boolean answeredQ5(EditText textBox) {
        String nullString = "";

        if (!textBox.getText().toString().equals(nullString)) {
            q5 = true;
            q_string = textBox.getText().toString();
            return true;
        } else {        // if there is no input
            q5 = false;
            return false;
        }
    }

    public boolean answerCorrectQ5() {
        EditText textboxS = findViewById(R.id.question_5_answer_1);
        EditText textboxL = findViewById(R.id.question_5_answer_2);
        EditText textboxR = findViewById(R.id.question_5_answer_3);

        checkAnswers(textboxS);
        checkAnswers(textboxL);
        checkAnswers(textboxR);

        if (answerCorrectQ5_A1 && answerCorrectQ5_A2 && answerCorrectQ5_A3) {
            return answerCorrectQ5 = true;
        }
        else {
            return answerCorrectQ5 = false;
        }
    }

    public void checkAnswers(EditText tB) {
        String answerInput = tB.getText().toString().toLowerCase();
        switch (tB.getId()) {
            case R.id.question_5_answer_1:
                answerCorrectQ5_A1 = checkAnswersForSLR( "single", answerInput);

            case R.id.question_5_answer_2:
                answerCorrectQ5_A2 = checkAnswersForSLR( "lens", answerInput);

            case R.id.question_5_answer_3:
                answerCorrectQ5_A3 = checkAnswersForSLR( "reflex", answerInput);
        }
    }

    public boolean checkAnswersForSLR(String correctAnswer, String answerInput) {
        if (correctAnswer.equalsIgnoreCase(answerInput)) {
            if (answerInput.contains(correctAnswer)) {           // correct
                return true;
            }
        }
        return false;
    }


    public void onButtonClicked(View view) {
        String checkBoxString = "android.support.v7.widget.AppCompatCheckBox";
        String radioButtonString = "android.support.v7.widget.AppCompatRadioButton";

        if (view.getClass().getName().equals(checkBoxString)) {
            Boolean checked = ((CheckBox) view).isChecked();
            if (checked) {
                switch (view.getTag().toString()) {
                    case "question 2":      // If Tag is for 2nd Question
                        q2 = true;
                        q2_checkedOptionsQuantity += 1;
                        switch (view.getId()) {
                            case R.id.question_2_option_1:      // if 1st Option...
                                if (q2_a2_stat == 1 && q2_checkedOptionsQuantity == 2) {      // Only Option Checked...
                                    answerCorrectQ2 = true;     // Correct Answers
                                } else {     // Second Option already Checked...
                                    q2_a1_stat = 1;
                                    answerCorrectQ2 = false;
                                }
                                break;
                            case R.id.question_2_option_2:      // if 2nd Option...
                                if (q2_a1_stat == 1 && q2_checkedOptionsQuantity == 2) {      // Only Option Checked...
                                    answerCorrectQ2 = true;     // Correct Answers
                                } else {        // Second Option already Checked...
                                    q2_a2_stat = 1;
                                    answerCorrectQ2 = false;
                                }
                                break;
                            case R.id.question_2_option_3:
                                answerCorrectQ2 = false;
                                break;
                            case R.id.question_2_option_4:
                                answerCorrectQ2 = false;
                                break;
                        }
                    case "question 4":      // If Tag is for 4th Question
                        q4 = true;
                        q4_checkedOptionsQuantity += 1;
                        switch (view.getId()) {
                            case R.id.question_4_option_1:
                                answerCorrectQ4 = false;
                                break;
                            case R.id.question_4_option_2:
                                answerCorrectQ4 = false;
                                break;
                            case R.id.question_4_option_3:
                                answerCorrectQ4 = false;
                                break;
                            case R.id.question_4_option_4:
                                answerCorrectQ4 = true;     // Correct Answer
                                break;
                        }
                }
            } else {        // Unchecked Object...
                switch (view.getTag().toString()) {
                    case "question 2":
                        if (q2_checkedOptionsQuantity > 0) {
                            q2_checkedOptionsQuantity -= 1;
                            if (q2_checkedOptionsQuantity == 0) {
                                q2 = false;
                                q2_a1_stat = 0;
                                q2_a2_stat = 0;
                            } else {
                                switch (view.getId()) {
                                    case R.id.question_2_option_1:      // if 1st Option...
                                        q2_a1_stat = 0;
                                        answerCorrectQ2 = false;
                                        break;
                                    case R.id.question_2_option_2:
                                        q2_a2_stat = 0;
                                        answerCorrectQ2 = false;
                                        break;
                                    case R.id.question_2_option_3:
                                        break;
                                    case R.id.question_2_option_4:
                                        break;
                                }
                            }
                        } else {
                            break;
                        }
                    case "question 4":
                        if (q4_checkedOptionsQuantity > 0) {
                            q4 = false;
                            q4_checkedOptionsQuantity -= 1;
                            break;
                        } else {
                            break;
                        }
                }
            }
        } else if (view.getClass().getName().equals(radioButtonString)) {
            Boolean checked = ((RadioButton) view).isChecked();
            if (checked) {
                switch (view.getTag().toString()) {
                    case "question 1":      // If Tag is for 1st Question
                        q1 = true;
                        q1_checkedOptionsQuantity += 1;
                        switch (view.getId()) {
                            case R.id.question_1_option_1:
                                answerCorrectQ1 = false;
                                break;
                            case R.id.question_1_option_2:
                                answerCorrectQ1 = false;
                                break;
                            case R.id.question_1_option_3:
                                answerCorrectQ1 = false;
                                break;
                            case R.id.question_1_option_4:
                                answerCorrectQ1 = true;     // Correct Answer
                                break;
                        }

                    case "question 3":      // If Tag is for 3rd Question
                        q3 = true;
                        q3_checkedOptionsQuantity += 1;
                        switch (view.getId()) {
                            case R.id.question_3_option_1:
                                answerCorrectQ3 = true;     // Correct Answer
                                break;
                            case R.id.question_3_option_2:
                                answerCorrectQ3 = false;
                                break;
                        }
                }
            } else {
                // TODO: find a way to determine whether q2 or q4, individually, is answered AFTER unchecking them
                switch (view.getTag().toString()) {
                    case "question 1":
                        q1 = false;
                        q1_checkedOptionsQuantity -= 1;
                        break;
                    case "question 3":
                        q3 = false;
                        q3_checkedOptionsQuantity -= 1;
                        break;
                }
            }
        }
    }
}