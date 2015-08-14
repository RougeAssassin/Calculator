/*
    Android calculator application
    Property of BlueTheta Software

    Written by: Jeff Booth, Stephen Leer, and Stanley Rohrbacher
    July, 2015

    Version: Pre-Alpha
*/

package com.bluetheta.calculator;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class BasicCalculator extends Activity {

    private LinearLayout containerLayout;



    private int buttonDimensions;




    // TextView holds the textViewDisplay view
    private TextView textViewDisplay;

    // Button arrays that store number and operator buttons
    private Button[] numberButtons = new Button[10];
    private Button[] operatorButtons = new Button[10];

    // Boolean object to determine if parenthesis are currently in use
    private boolean parenthesisOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_calculator);






        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;






        containerLayout = (LinearLayout)findViewById(R.id.buttonLayout);
        buttonDimensions = width / 4;

        // Create the TextView textViewDisplay object
        textViewDisplay = (TextView)findViewById(R.id.displayView);

        // Create numeric Button objects and set their action listeners
        numberButtons[0] = (Button)findViewById(R.id.button_numberZero);
        numberButtons[0].setLayoutParams(new TableRow.LayoutParams(buttonDimensions, buttonDimensions));
        numberButtons[0].setOnClickListener(new NumberButtonClick());

        numberButtons[1] = (Button)findViewById(R.id.button_numberOne);
        numberButtons[1].setLayoutParams(new TableRow.LayoutParams(buttonDimensions, buttonDimensions));
        numberButtons[1].setOnClickListener(new NumberButtonClick());

        numberButtons[2] = (Button)findViewById(R.id.button_numberTwo);
        numberButtons[2].setLayoutParams(new TableRow.LayoutParams(buttonDimensions, buttonDimensions));
        numberButtons[2].setOnClickListener(new NumberButtonClick());

        numberButtons[3] = (Button)findViewById(R.id.button_numberThree);
        numberButtons[3].setLayoutParams(new TableRow.LayoutParams(buttonDimensions, buttonDimensions));
        numberButtons[3].setOnClickListener(new NumberButtonClick());

        numberButtons[4] = (Button)findViewById(R.id.button_numberFour);
        numberButtons[4].setLayoutParams(new TableRow.LayoutParams(buttonDimensions, buttonDimensions));
        numberButtons[4].setOnClickListener(new NumberButtonClick());

        numberButtons[5] = (Button)findViewById(R.id.button_numberFive);
        numberButtons[5].setLayoutParams(new TableRow.LayoutParams(buttonDimensions, buttonDimensions));
        numberButtons[5].setOnClickListener(new NumberButtonClick());

        numberButtons[6] = (Button)findViewById(R.id.button_numberSix);
        numberButtons[6].setLayoutParams(new TableRow.LayoutParams(buttonDimensions, buttonDimensions));
        numberButtons[6].setOnClickListener(new NumberButtonClick());

        numberButtons[7] = (Button)findViewById(R.id.button_numberSeven);
        numberButtons[7].setLayoutParams(new TableRow.LayoutParams(buttonDimensions, buttonDimensions));
        numberButtons[7].setOnClickListener(new NumberButtonClick());

        numberButtons[8] = (Button)findViewById(R.id.button_numberEight);
        numberButtons[8].setLayoutParams(new TableRow.LayoutParams(buttonDimensions, buttonDimensions));
        numberButtons[8].setOnClickListener(new NumberButtonClick());

        numberButtons[9] = (Button)findViewById(R.id.button_numberNine);
        numberButtons[9].setLayoutParams(new TableRow.LayoutParams(buttonDimensions, buttonDimensions));
        numberButtons[9].setOnClickListener(new NumberButtonClick());


        // Create the operator Button objects and set their action listeners
        operatorButtons[0] = (Button)findViewById(R.id.button_decimal);
        operatorButtons[0].setOnClickListener(new OperatorButtonClick());

        operatorButtons[1] = (Button)findViewById(R.id.button_addition);
        operatorButtons[1].setOnClickListener(new OperatorButtonClick());

        operatorButtons[2] = (Button)findViewById(R.id.button_subtraction);
        operatorButtons[2].setOnClickListener(new OperatorButtonClick());

        operatorButtons[3] = (Button)findViewById(R.id.button_multiplication);
        operatorButtons[3].setOnClickListener(new OperatorButtonClick());

        operatorButtons[4] = (Button)findViewById(R.id.button_division);
        operatorButtons[4].setOnClickListener(new OperatorButtonClick());

        operatorButtons[5] = (Button)findViewById(R.id.button_parenthesis);
        operatorButtons[5].setOnClickListener(new OperatorButtonClick());

        operatorButtons[6] = (Button)findViewById(R.id.button_signChange);
        operatorButtons[6].setOnClickListener(new OperatorButtonClick());

        operatorButtons[7] = (Button)findViewById(R.id.button_equals);
        operatorButtons[7].setOnClickListener(new OperatorButtonClick());

        operatorButtons[8] = (Button)findViewById(R.id.button_backspace);
        operatorButtons[8].setOnClickListener(new OperatorButtonClick());

        operatorButtons[9] = (Button)findViewById(R.id.button_clear);
        operatorButtons[9].setOnClickListener(new OperatorButtonClick());

    }


    /**
     * OnClickListener class that edits the textViewDisplay TextView when a numeric Button object is
     * clicked.
     */

    private class NumberButtonClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Button numButton = (Button)v;
            String displayFieldText = textViewDisplay.getText().toString();

            if (displayFieldText.equals("0"))
                textViewDisplay.setText(numButton.getText());
            else {
                displayFieldText += numButton.getText();
                textViewDisplay.setText(displayFieldText);
            }

        }

    }

    /**
     * OnClickListener class that edits the textViewDisplay TextView when an operator Button object is
     * clicked.
     */

    private class OperatorButtonClick implements View.OnClickListener {

        public void onClick(View v) {

            Button operatorClicked = (Button)v;
            String operatorSymbol = operatorClicked.getText().toString();

            switch (operatorSymbol) {
                case "+":
                    textViewDisplay.setText(textViewDisplay.getText().toString() + "+");
                    break;
                case "-":
                    textViewDisplay.setText(textViewDisplay.getText().toString() + "-");
                    break;
                case "*":
                    textViewDisplay.setText(textViewDisplay.getText().toString() + "*");
                    break;
                case "/":
                    textViewDisplay.setText(textViewDisplay.getText().toString() + "/");
                    break;
                case ".":
                    textViewDisplay.setText(textViewDisplay.getText().toString()+ ".");
                    break;
                case "+/-":
                    //TODO Add sign change functionality
                    break;
                case "(  )":
                    if(textViewDisplay.getText().toString().equals("0")) {
                        textViewDisplay.setText("(");
                        parenthesisOpen = true;
                    }
                    else if(!parenthesisOpen) {
                        textViewDisplay.setText(textViewDisplay.getText().toString().concat("("));
                        parenthesisOpen = true;
                    }
                    else {
                        textViewDisplay.setText(textViewDisplay.getText().toString().concat(")"));
                    }
                    break;
                case "DEL":
                    StringBuilder currentDisplay = new StringBuilder(textViewDisplay.getText().toString());
                    if(textViewDisplay.getText().toString().length() == 1)
                        textViewDisplay.setText("0");
                    else {
                        currentDisplay.deleteCharAt(textViewDisplay.getText().toString().length() - 1);
                        textViewDisplay.setText(currentDisplay.toString());
                    }
                    break;
                case "=":
                    //TODO Add equals operator functionality
                    break;
                case "C":
                    textViewDisplay.setText("0");
                    break;
            }

        }

    }
}
