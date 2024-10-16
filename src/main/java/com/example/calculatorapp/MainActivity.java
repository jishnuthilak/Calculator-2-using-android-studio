package com.example.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private double firstOperand = 0;
    private double secondOperand = 0;
    private String operator = "";
    private boolean isSecondOperand = false;
    private String currentInput = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.editText);

        // Set listeners for all buttons
        setButtonListeners();
    }

    private void setButtonListeners() {
        // Numbers
        int[] numberButtons = {R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
                R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9};

        for (int id : numberButtons) {
            findViewById(id).setOnClickListener(this::onNumberClick);
        }

        // Operations
        findViewById(R.id.buttonAdd).setOnClickListener(this::onOperatorClick);
        findViewById(R.id.buttonMinus).setOnClickListener(this::onOperatorClick);
        findViewById(R.id.buttonMultiply).setOnClickListener(this::onOperatorClick);
        findViewById(R.id.buttonDivide).setOnClickListener(this::onOperatorClick);

        // Special buttons
        findViewById(R.id.buttonEqual).setOnClickListener(this::onEqualClick);
        findViewById(R.id.buttonClear).setOnClickListener(v -> clearDisplay());
    }

    // Handle number button clicks
    private void onNumberClick(View v) {
        Button b = (Button) v;
        currentInput += b.getText().toString();
        display.setText(currentInput);
    }

    // Handle operator button clicks
    private void onOperatorClick(View v) {
        Button b = (Button) v;
        firstOperand = Double.parseDouble(currentInput);  // Store the first operand
        operator = b.getText().toString();  // Set the operator
        currentInput = "";  // Reset input for the next number
        isSecondOperand = true;
    }

    // Handle the equal button click
    private void onEqualClick(View v) {
        secondOperand = Double.parseDouble(currentInput);  // Store the second operand
        double result = performCalculation();  // Perform the calculation
        display.setText(String.valueOf(result));  // Display the result
        currentInput = String.valueOf(result);  // Store the result for further operations
    }

    // Perform the calculation based on the operator
    private double performCalculation() {
        double result = 0;
        switch (operator) {
            case "+":
                result = firstOperand + secondOperand;
                break;
            case "-":
                result = firstOperand - secondOperand;
                break;
            case "*":
                result = firstOperand * secondOperand;
                break;
            case "/":
                if (secondOperand != 0) {
                    result = firstOperand / secondOperand;
                } else {
                    throw new ArithmeticException("Division by zero is not allowed.");
                }
                break;
        }
        clearOperands(); // Reset after calculation
        return result;
    }

    // Clear the current operands and operator
    private void clearOperands() {
        firstOperand = 0;
        secondOperand = 0;
        operator = "";
        isSecondOperand = false;
    }

    // Clear the display and reset calculator state
    private void clearDisplay() {
        currentInput = "";
        clearOperands();  // Reset the calculator state
        display.setText("0");  // Clear the display
    }
}
