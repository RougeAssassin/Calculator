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
import android.widget.TableRow;
import android.widget.TextView;

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.StringTokenizer;

public class BasicCalculator extends Activity {

    // Magic integer that gives Stanley his square buttons
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
        operatorButtons[0].setLayoutParams(new TableRow.LayoutParams(buttonDimensions, buttonDimensions));
        operatorButtons[0].setOnClickListener(new OperatorButtonClick());

        operatorButtons[1] = (Button)findViewById(R.id.button_addition);
        operatorButtons[1].setLayoutParams(new TableRow.LayoutParams(buttonDimensions, buttonDimensions));
        operatorButtons[1].setOnClickListener(new OperatorButtonClick());

        operatorButtons[2] = (Button)findViewById(R.id.button_subtraction);
        operatorButtons[2].setLayoutParams(new TableRow.LayoutParams(buttonDimensions, buttonDimensions));
        operatorButtons[2].setOnClickListener(new OperatorButtonClick());

        operatorButtons[3] = (Button)findViewById(R.id.button_multiplication);
        operatorButtons[3].setLayoutParams(new TableRow.LayoutParams(buttonDimensions, buttonDimensions));
        operatorButtons[3].setOnClickListener(new OperatorButtonClick());

        operatorButtons[4] = (Button)findViewById(R.id.button_division);
        operatorButtons[4].setLayoutParams(new TableRow.LayoutParams(buttonDimensions, buttonDimensions));
        operatorButtons[4].setOnClickListener(new OperatorButtonClick());

        operatorButtons[5] = (Button)findViewById(R.id.button_parenthesis);
        operatorButtons[5].setLayoutParams(new TableRow.LayoutParams(buttonDimensions, buttonDimensions));
        operatorButtons[5].setOnClickListener(new OperatorButtonClick());

        operatorButtons[6] = (Button)findViewById(R.id.button_signChange);
        operatorButtons[6].setLayoutParams(new TableRow.LayoutParams(buttonDimensions, buttonDimensions));
        operatorButtons[6].setOnClickListener(new OperatorButtonClick());

        operatorButtons[7] = (Button)findViewById(R.id.button_equals);
        operatorButtons[7].setLayoutParams(new TableRow.LayoutParams(buttonDimensions, buttonDimensions));
        operatorButtons[7].setOnClickListener(new OperatorButtonClick());

        operatorButtons[8] = (Button)findViewById(R.id.button_backspace);
        operatorButtons[8].setLayoutParams(new TableRow.LayoutParams(buttonDimensions, buttonDimensions));
        operatorButtons[8].setOnClickListener(new OperatorButtonClick());

        operatorButtons[9] = (Button)findViewById(R.id.button_clear);
        operatorButtons[9].setLayoutParams(new TableRow.LayoutParams(buttonDimensions, buttonDimensions));
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
                    String userInput = textViewDisplay.getText().toString();

                    if(userInput.equals("0")) {
                        textViewDisplay.setText("(");
                        parenthesisOpen = true;
                    }
                    else if(!parenthesisOpen) {
                        textViewDisplay.setText(textViewDisplay.getText().toString() + "(");
                        parenthesisOpen = true;
                    }
                    else {
                        textViewDisplay.setText(textViewDisplay.getText().toString() + ")");
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
                    String input = textViewDisplay.getText().toString();
                    String postfix = toPostfix(input);
                    int answer = computePostfix(postfix);
                    textViewDisplay.setText(input + "\n=" + Integer.toString(answer) + "\n");
                    break;
                case "C":
                    textViewDisplay.setText("0");
                    break;
            }

        }

    }

    /**
     * Converts an infix expression to a postfix expression
     *
     * @param infix
     *            the string that contains the infix expression
     * @return a string that contains the expression written in postfix notation
     * @throws ExpressionFormatException
     *             if the infix expression is invalid
     */
    public static String toPostfix(String infix) {

        try {
            String postfix = "";
            boolean unary = true; // is the current operator unary?
            Stack<String> stack = new Stack<String>();
            StringTokenizer st = new StringTokenizer(infix, "()+-/%* ", true);
            while (st.hasMoreTokens()) {
                String token = st.nextToken().trim();
                if (token.equals("")) { // skip any empty token
                } else if (token.equals("(")) {
                    stack.push(token);
                } else if (token.equals(")")) {
                    String op;
                    while (!(op = stack.pop()).equals("(")) {
                        postfix += " " + op;
                    }
                } else if (token.equals("*")
                        || // an operator
                        token.equals("+") || token.equals("-")
                        || token.equals("%") || token.equals("/")) {
                    if (unary) {
                        token = "u" + token;
                        // a unary op always goes on
                        // the stack without popping any other op
                        stack.push(token);
                    } else {
                        int p = operatorPrecedence(token);
                        while (!stack.isEmpty() && !stack.peek().equals("(")
                                && operatorPrecedence(stack.peek()) >= p) {
                            String op = stack.pop();
                            postfix += " " + op;
                        }
                        stack.push(token);
                    }
                    unary = true; // if an operator is after this one, it
                    // has to be unary
                } else { // an operand
                    Integer.parseInt(token); // just to check that
                    // it is a number
                    // If not a number, an exception is
                    // thrown
                    postfix += " " + token;
                    unary = false; // any operator after an operand is binary
                }
            }
            while (!stack.isEmpty()) {
                String op = stack.pop();
                postfix += " " + op;
            }
            return postfix;
        } catch (EmptyStackException ese) {
            throw new ExpressionFormatException();
        } catch (NumberFormatException nfe) {
            throw new ExpressionFormatException();
        }
    }

    /**
     * Evaluates a postfix expression
     *
     * @param postfix
     *            the string that contains the postfix expression
     * @return the integer value of the expression
     * @throws ExpressionFormatException
     *             if the postfix expression is invalid
     */
    public static int computePostfix(String postfix) {

        try {
            Stack<Integer> stack = new Stack<Integer>();
            StringTokenizer st = new StringTokenizer(postfix);
            while (st.hasMoreTokens()) {
                String token = st.nextToken();
                if (token.equals("*")
                        || // an operator
                        token.equals("+") || token.equals("-")
                        || token.equals("%") || token.equals("/")
                        || token.equals("u+") || token.equals("u-")) {
                    applyOperator(token, stack);
                } else { // an operand
                    stack.push(new Integer(token));
                }
            }
            int result = stack.pop();
            if (!stack.isEmpty()) { // the stack should be empty
                throw new ExpressionFormatException();
            }
            return result;
        } catch (EmptyStackException ese) {
            throw new ExpressionFormatException();
        } catch (NumberFormatException nfe) {
            throw new ExpressionFormatException();
        }
    }

    /**
     * Applies the given operator to the top operand or the top two operands on
     * the given stack. Possible operators are unary + and - written as "u+" and
     * "u-", and binary "+", "-", "%", and "/"
     *
     * @param operator
     *            the operator to apply
     * @param s
     *            the stack of the operands
     * @throws EmptyStackException
     *             if the stack is empty
     * @throws IllegalArgumentException
     *             if the operator is not /,*,%,+,-,u-,u+
     *
     *             post condition: the operator is applied to the top operand or
     *             to the top two operands on the stack. The operand(s) is/are
     *             popped from the stack. The result is pushed on the stack
     */
    private static void applyOperator(String operator, Stack<Integer> s) {
        int op1 = s.pop();
        if (operator.equals("u-")) {
            s.push(-op1);
        } else if (operator.equals("u+")) {
            s.push(op1);
        } else { // binary operator
            int op2 = s.pop();
            int result;
            if (operator.equals("+")) {
                result = op2 + op1;
            } else if (operator.equals("-")) {
                result = op2 - op1;
            } else if (operator.equals("/")) {
                result = op2 / op1;
            } else if (operator.equals("%")) {
                result = op2 % op1;
            } else if (operator.equals("*")) {
                result = op2 * op1;
            } else {
                throw new IllegalArgumentException();
            }
            s.push(result);
        }
    }

    /**
     * Returns an integer indicating the order of precedence of an operator
     * given as a string. Unary + and - return 2, *, / and % return 1 and binary
     * + and - return 0.
     *
     * @param operator
     *            the operator given as a string
     *
     * @return the precedence order of a given operator
     */
    private static int operatorPrecedence(String operator) {
        if (operator.equals("u-") || operator.equals("u+")) {
            return 2;
        } else if (operator.equals("*") || operator.equals("/")
                || operator.equals("%")) {
            return 1;
        } else if (operator.equals("-") || operator.equals("+")) {
            return 0;
        } else {
            throw new ExpressionFormatException();
        }
    }

}
