package com.example.mysimplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class MainActivity extends AppCompatActivity {
    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // assigns the display attribute
        display = findViewById(R.id.displayScreen);
        //prevents keyboard from popping up
        display.setShowSoftInputOnFocus(false);
        //Erases the "Enter input here" once the user tries to enter input
        display.setOnClickListener(v -> {
            if (getString(R.string.display).equals(display.getText().toString()) || getString(R.string.exception).equals(display.getText().toString()))
                display.setText("");
        });
    }

    //Takes in a new string which we add to display
    private void updateText(String strToAdd) {
        String myString = display.getText().toString();
        //We want to add a number in between a set of other numbers Ie: 7+4+3+2 ----> 7+4+6+3+2. We can achieve this by dividing the string into 2
        int cursorposition = display.getSelectionStart();
        String leftString = myString.substring(0, cursorposition);
        String rightString = myString.substring(cursorposition);
        //check to see if the default text "Enter A Value" or the exception text "Please Enter A Valid Arithmetic Operation" is on the screen
        if (getString(R.string.display).equals(display.getText().toString()) || getString(R.string.exception).equals(display.getText().toString())) {
            // The first number or character that the user wants to add into the display
            display.setText((strToAdd));

        } else {
            // if the user already has characters on display and it's not the default message and we want to add numbers in between Ie: 7+4+3+2 ----> 7+4+6+3+2
            display.setText(String.format("%s%s%s", leftString, strToAdd, rightString));

        }
        //make cursor shift to the right instead of the left by increasing cursor position by 1
        display.setSelection(cursorposition + 1);
    }

    public void oneBTN(View button) {
        updateText("1");

    }

    public void twoBTN(View button) {
        updateText("2");

    }

    public void threeBTN(View button) {
        updateText("3");

    }

    public void additionBTN(View button) {
        updateText("+");

    }

    public void fourBTN(View button) {
        updateText("4");

    }

    public void fiveBTN(View button) {
        updateText("5");

    }

    public void sixBTN(View button) {
        updateText("6");

    }

    public void subtractionBTN(View button) {
        updateText("-");
    }

    public void sevenBTN(View button) {
        updateText("7");
    }

    public void eightBTN(View button) {
        updateText("8");
    }

    public void nineBTN(View button) {
        updateText("9");

    }

    public void multiplicationBTN(View button) {
        updateText("*");
    }

    public void clearBTN(View button) {
        display.setText("");
    }

    public void zeroBTN(View button) {
        updateText("0");
    }

    public void decimalBTN(View button) {
        updateText(".");
    }

    public void divisionBTN(View button) {
        updateText("/");
    }

    @SuppressLint("SetTextI18n")
    public void equalBTN(View button) {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("rhino");
        if (!(display.getText().toString().equals(""))) {
            try {
                double result = (double) engine.eval(display.getText().toString());
                if (result == Math.floor(result)){
                    display.setText(String.valueOf((int) result));
                    display.setSelection(String.valueOf((int) result).length());
                }
                else{
                    display.setText(String.valueOf(result));
                    display.setSelection(String.valueOf(result).length());
                }
                //updates the cursor to the end of the final answer
            } catch (ScriptException e) {
                display.setText(R.string.exception);
            }
        }
    }
}