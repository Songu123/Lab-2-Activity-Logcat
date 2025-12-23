package com.son.lab_2_activity_logcat;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity1 extends AppCompatActivity implements View.OnClickListener {
//    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnAdd, btnSub, btnMul, btnDiv, btnEqual, btnClear;
    private TextView tvResult;

    private String currentNumber = "";
    private double firstNumber = 0;
    private String operator = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learn_linear_layout);

        tvResult = findViewById(R.id.tvResult);

//        Button number
        int[] numberBtnIds = {R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btn0};

        for (int btnId : numberBtnIds) {
            Button btn = findViewById(btnId);
            btn.setOnClickListener(this);
        }

        findViewById(R.id.btnAC).setOnClickListener(this);
        findViewById(R.id.btnAdd).setOnClickListener(this);
        findViewById(R.id.btnAddMinus).setOnClickListener(this);
        findViewById(R.id.btnMinus).setOnClickListener(this);
        findViewById(R.id.btnMultiply).setOnClickListener(this);
        findViewById(R.id.btnDelete).setOnClickListener(this);
        findViewById(R.id.btnDivide).setOnClickListener(this);
        findViewById(R.id.btnEqual).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Button btn = (Button) v;
        String btnText = btn.getText().toString();

        // Nhập số
        if (btnText.matches("[0-9]")) {
            currentNumber += btnText;
            tvResult.setText(currentNumber);
            return;
        }

        // AC
        if (btnText.equals("AC")) {
            resetCalculator();
            return;
        }

        // +/- đổi dấu
        if (btnText.equals("+/-")) {
            if (!currentNumber.isEmpty()) {
                double number = Double.parseDouble(currentNumber);
                number = -number;
                currentNumber = removeDotZero(number);
                tvResult.setText(currentNumber);
            }
            return;
        }

        // %
        if (btnText.equals("%")) {
            if (!currentNumber.isEmpty()) {
                double number = Double.parseDouble(currentNumber) / 100;
                currentNumber = removeDotZero(number);
                tvResult.setText(currentNumber);
            }
            return;
        }

        // DEL
        if (btnText.equals("DEL")) {
            if (!currentNumber.isEmpty()) {
                currentNumber = currentNumber.substring(0, currentNumber.length() - 1);
                tvResult.setText(currentNumber.isEmpty() ? "0" : currentNumber);
            }
            return;
        }

        // =
        if (btnText.equals("=")) {
            if (currentNumber.isEmpty() || operator.isEmpty()) return;

            double secondNumber = Double.parseDouble(currentNumber);
            double result = calculate(firstNumber, secondNumber, operator);

            tvResult.setText(removeDotZero(result));
            currentNumber = String.valueOf(result);
            operator = "";
            return;
        }

        // Toán tử + - * /
        if ("+-*/".contains(btnText)) {
            if (currentNumber.isEmpty()) return;

            firstNumber = Double.parseDouble(currentNumber);
            operator = btnText;
            currentNumber = "";
        }
    }

    private double calculate(double a, double b, String op) {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/":
                if (b == 0) {
                    Toast.makeText(this, "Không thể chia cho 0", Toast.LENGTH_SHORT).show();
                    return 0;
                }
                return a / b;
        }
        return 0;
    }

    private void resetCalculator() {
        currentNumber = "";
        firstNumber = 0;
        operator = "";
        tvResult.setText("0");
    }

    private String removeDotZero(double value) {
        if (value == (long) value)
            return String.valueOf((long) value);
        return String.valueOf(value);
    }

}
