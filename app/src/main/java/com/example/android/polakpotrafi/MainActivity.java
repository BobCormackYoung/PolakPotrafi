package com.example.android.polakpotrafi;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.textAppearanceMedium;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // Initializing the tacker for whether the scores have been checked
    boolean scoresCheckedTrigger = false;
    boolean scoresCorrectTrigger = false;
    double scoresPercentage = 0;

    /**
     * Checking the scores by calling individual question checking methods
     */
    public void checkScores (View view) {
        //Initialize Answers
        CheckBox answer1aCheckBox = (CheckBox) findViewById(R.id.answer_1a_id);
        CheckBox answer1bCheckBox = (CheckBox) findViewById(R.id.answer_1b_id);
        CheckBox answer1cCheckBox = (CheckBox) findViewById(R.id.answer_1c_id);
        RadioButton answer2aRadioButton = (RadioButton) findViewById(R.id.answer_2a_id);
        RadioButton answer2bRadioButton = (RadioButton) findViewById(R.id.answer_2b_id);
        RadioButton answer2cRadioButton = (RadioButton) findViewById(R.id.answer_2c_id);
        EditText answer3EditText = (EditText) findViewById(R.id.answer_3a_id);
        RadioButton answer4aRadioButton = (RadioButton) findViewById(R.id.answer_4a_id);
        RadioButton answer4bRadioButton = (RadioButton) findViewById(R.id.answer_4b_id);

        // reset colors and triggers
        resetTriggers();
        resetCheckBoxColors(answer1aCheckBox);
        resetCheckBoxColors(answer1bCheckBox);
        resetCheckBoxColors(answer1cCheckBox);
        resetRadioButtonColors(answer2aRadioButton);
        resetRadioButtonColors(answer2bRadioButton);
        resetRadioButtonColors(answer2cRadioButton);
        resetEditTextColors(answer3EditText);
        resetRadioButtonColors(answer4aRadioButton);
        resetRadioButtonColors(answer4bRadioButton);

        // Call individual question checkers
        boolean question_one = checkQuestionOne(answer1aCheckBox, answer1bCheckBox, answer1cCheckBox);
        boolean question_two = checkQuestionTwo(answer2aRadioButton, answer2bRadioButton, answer2cRadioButton);
        boolean question_three = checkQuestionThree(answer3EditText);
        boolean question_four = checkQuestionFour(answer4aRadioButton, answer4bRadioButton);

        // If all answer are correct
        if (question_one && question_two && question_three && question_four) {
            scoresCheckedTrigger = true;
            scoresCorrectTrigger = true;
            scoresPercentage = 100;
            Context context = getApplicationContext();
            CharSequence resultText = "You got everything correct! Well done :D";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, resultText, duration);
            toast.show();
        } else {
            scoresCheckedTrigger = true;
            scoresCorrectTrigger = false;
            Context context = getApplicationContext();
            CharSequence resultText = "You got a few wrong. Hit Reset to try again";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, resultText, duration);
            toast.show();
        }
    }

    /**
     * Check result of Question 1
     *
     * @param answer1aCheckBox input for the check box 1 answer
     * @param answer1bCheckBox input for the check box 2 answer
     * @param answer1cCheckBox input for the check box 3 answer
     * @return true or false statement of grading
     */
    private boolean checkQuestionOne(CheckBox answer1aCheckBox, CheckBox answer1bCheckBox, CheckBox answer1cCheckBox) {
        //initialize return value
        boolean result = false;

        //check status of checkboxes
        boolean answer1aIsChecked = answer1aCheckBox.isChecked();
        boolean answer1bIsChecked = answer1bCheckBox.isChecked();
        boolean answer1cIsChecked = answer1cCheckBox.isChecked();

        //check if answer is correct
        if (answer1aIsChecked && answer1cIsChecked && !answer1bIsChecked) {
            result = true;
            answer1aCheckBox.setTextColor(getResources().getColor(R.color.correctAnswer));
            answer1cCheckBox.setTextColor(getResources().getColor(R.color.correctAnswer));
        } else {
            result = false;
            answer1aCheckBox.setTextColor(getResources().getColor(R.color.incorrectAnswer));
            answer1bCheckBox.setTextColor(getResources().getColor(R.color.incorrectAnswer));
            answer1cCheckBox.setTextColor(getResources().getColor(R.color.incorrectAnswer));
        }
        return result;
    }

    /**
     * Check result of Question 2
     *
     * @param answer2aRadioButton input for radio button 1 answer
     * @param answer2bRadioButton input for radio button 2 answer
     * @param answer2cRadioButton input for radio button 3 answer
     * @return true or false statement of grading
     */
    private boolean checkQuestionTwo(RadioButton answer2aRadioButton,RadioButton answer2bRadioButton,RadioButton answer2cRadioButton) {
        //initialize return value
        boolean result = false;

        //check status of radiobutton
        boolean answer2bIsChecked = answer2bRadioButton.isChecked();

        //check if answer is correct
        if (answer2bIsChecked) {
            result = true;
            answer2bRadioButton.setTextColor(getResources().getColor(R.color.correctAnswer));
        } else {
            result = false;
            answer2aRadioButton.setTextColor(getResources().getColor(R.color.incorrectAnswer));
            answer2bRadioButton.setTextColor(getResources().getColor(R.color.incorrectAnswer));
            answer2cRadioButton.setTextColor(getResources().getColor(R.color.incorrectAnswer));
        }

        return result;
    }

    /**
     * Check result of Question 3
     *
     * @param answer3EditText input for edit text answer
     * @return true or false statement of grading
     */
    private boolean checkQuestionThree(EditText answer3EditText) {
        String answer3Correct = "EAGLE";

        //initialize return value
        boolean result = false;

        //Get input answer
        String answer3Text = answer3EditText.getText().toString().toUpperCase();

        if (answer3Text.equals(answer3Correct)) {
            result = true;
            answer3EditText.setTextColor(getResources().getColor(R.color.correctAnswer));
        } else {
            result = false;
            answer3EditText.setTextColor(getResources().getColor(R.color.incorrectAnswer));
        }

        return result;
    }

    /**
     * Check result of Question 4
     *
     * @param answer4aRadioButton input for radio button 1 answer
     * @param answer4bRadioButton input for radio button 2 answer
     * @return true or false statement of grading
     */
    private boolean checkQuestionFour(RadioButton answer4aRadioButton,RadioButton answer4bRadioButton) {
        //initialize return value
        boolean result = false;

        //check status of radiobutton
        boolean answer4aIsChecked = answer4aRadioButton.isChecked();

        //check if answer is correct
        if (answer4aIsChecked) {
            result = true;
            answer4aRadioButton.setTextColor(getResources().getColor(R.color.correctAnswer));
        } else {
            result = false;
            answer4aRadioButton.setTextColor(getResources().getColor(R.color.incorrectAnswer));
            answer4bRadioButton.setTextColor(getResources().getColor(R.color.incorrectAnswer));
        }

        return result;
    }

    public void resetScores (View view) {
        resetTriggers();
        CheckBox answer1aCheckBox = (CheckBox) findViewById(R.id.answer_1a_id);
        CheckBox answer1bCheckBox = (CheckBox) findViewById(R.id.answer_1b_id);
        CheckBox answer1cCheckBox = (CheckBox) findViewById(R.id.answer_1c_id);
        resetCheckBoxColors(answer1aCheckBox);
        resetCheckBoxColors(answer1bCheckBox);
        resetCheckBoxColors(answer1cCheckBox);
        resetCheckBoxCheck(answer1aCheckBox);
        resetCheckBoxCheck(answer1bCheckBox);
        resetCheckBoxCheck(answer1cCheckBox);

        RadioButton answer2aRadioButton = (RadioButton) findViewById(R.id.answer_2a_id);
        RadioButton answer2bRadioButton = (RadioButton) findViewById(R.id.answer_2b_id);
        RadioButton answer2cRadioButton = (RadioButton) findViewById(R.id.answer_2c_id);
        resetRadioButtonColors(answer2aRadioButton);
        resetRadioButtonColors(answer2bRadioButton);
        resetRadioButtonColors(answer2cRadioButton);

        RadioButton answer4aRadioButton = (RadioButton) findViewById(R.id.answer_4a_id);
        RadioButton answer4bRadioButton = (RadioButton) findViewById(R.id.answer_4b_id);
        resetRadioButtonColors(answer4aRadioButton);
        resetRadioButtonColors(answer4bRadioButton);

        EditText answer3EditText = (EditText) findViewById(R.id.answer_3a_id);
        resetEditTextColors(answer3EditText);
        resetEditTextText(answer3EditText);
    }

    private void resetTriggers(){
        scoresCheckedTrigger = false;
        scoresCorrectTrigger = false;
        scoresPercentage = 0;
    }

    private void resetCheckBoxColors(CheckBox inputCheckBox){
        inputCheckBox.setTextColor(getResources().getColor(R.color.resetAnswer));
    }

    private void resetCheckBoxCheck(CheckBox inputCheckBox){
        inputCheckBox.setChecked(false);
    }

    private void resetRadioButtonColors(RadioButton inputRadioButton){
        inputRadioButton.setTextColor(getResources().getColor(R.color.resetAnswer));
    }

    private void resetEditTextColors(EditText inputEditText) {
        inputEditText.setTextColor(getResources().getColor(R.color.resetAnswer));
    }

    private void resetEditTextText(EditText inputEditText) {
        inputEditText.setText("");
        //inputEditText.clearComposingText();
    }

    public void clickEditText(View view){
        EditText answer3EditText = (EditText) findViewById(R.id.answer_3a_id);
        resetEditTextColors(answer3EditText);
        resetEditTextText(answer3EditText);
    }

    public void shareScores (View view) {
    // TODO: add ability to share
    }

}
