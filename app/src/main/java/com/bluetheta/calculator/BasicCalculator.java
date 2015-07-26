/*
    Android calculator application
    Property of BlueTheta Software

    Written by: Jeff Booth, Stephen Leer, and Stanley Rohrbacher
    July, 2015

    Version: Pre-Alpha
*/

package com.bluetheta.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BasicCalculator extends Activity {

    // TextView holds the display view
    private TextView display;

    // Button arrays that store number and operator buttons
    private Button[] numberButtons = new Button[10];
    private Button[] operatorButtons = new Button[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_calculator);

        // Create the TextView display object
        display = (TextView)findViewById(R.id.displayView);

        // Create numeric Button objects and set their action listeners
        numberButtons[0] = (Button)findViewById(R.id.button_numberZero);
        numberButtons[0].setOnClickListener(new NumberButtonClick());
        numberButtons[1] = (Button)findViewById(R.id.button_numberOne);
        numberButtons[1].setOnClickListener(new NumberButtonClick());
        numberButtons[2] = (Button)findViewById(R.id.button_numberTwo);
        numberButtons[2].setOnClickListener(new NumberButtonClick());
        numberButtons[3] = (Button)findViewById(R.id.button_numberThree);
        numberButtons[3].setOnClickListener(new NumberButtonClick());
        numberButtons[4] = (Button)findViewById(R.id.button_numberFour);
        numberButtons[4].setOnClickListener(new NumberButtonClick());
        numberButtons[5] = (Button)findViewById(R.id.button_numberFive);
        numberButtons[5].setOnClickListener(new NumberButtonClick());
        numberButtons[6] = (Button)findViewById(R.id.button_numberSix);
        numberButtons[6].setOnClickListener(new NumberButtonClick());
        numberButtons[7] = (Button)findViewById(R.id.button_numberSeven);
        numberButtons[7].setOnClickListener(new NumberButtonClick());
        numberButtons[8] = (Button)findViewById(R.id.button_numberEight);
        numberButtons[8].setOnClickListener(new NumberButtonClick());
        numberButtons[9] = (Button)findViewById(R.id.button_numberNine);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_basic_calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * OnClickListener class that edits the display TextView when a numeric Button object is
     * clicked.
     */

    private class NumberButtonClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Button numButton = (Button)v;
            String displayFieldText = display.getText().toString();

            if (displayFieldText.equals("0"))
                display.setText(numButton.getText());
            else {
                displayFieldText += numButton.getText();
                display.setText(displayFieldText);
            }

        }

    }

    /**
     * OnClickListener class that edits the display TextView when an operator Button object is
     * clicked.
     */

    private class OperatorButtonClick implements View.OnClickListener {

        public void onClick(View v) {

            Button operatorClicked = (Button)v;
            String operatorSymbol = operatorClicked.getText().toString();

            switch (operatorSymbol) {
                case "+":
                    display.setText(display.getText().toString() + " + ");
                    break;
                case "-":
                    display.setText(display.getText().toString() + " - ");
                    break;
                case "*":
                    display.setText(display.getText().toString() + " * ");
                    break;
                case "/":
                    display.setText(display.getText().toString() + " / ");
                    break;
                case ".":
                    display.setText(display.getText().toString()+ " . ");
                    break;
                case "+/-":
                    //TODO Add sign change operator functionality
                    break;
                case "(  )":
                    //TODO Add parenthesis operator functionality
                    break;
                case "DEL":
                    //TODO Add delete operator functionality
                    break;
                case "=":
                    //TODO Add equals operator functionality
                    break;
                case "C":
                    display.setText("0");
                    break;
            }

        }

    }
}
