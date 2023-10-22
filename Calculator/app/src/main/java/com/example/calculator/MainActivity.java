package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    Button btn_memory_read, btn_memory_save, btn_memory_clear, btn_add, btn_subtract, btn_multiply, btn_divide, button_equals;
    Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_0, button_clear, btn_dot;
    TextView inputExpression;
    TextView answer;
    String memoryValue = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_memory_read = findViewById(R.id.btn_memory_read);
        btn_memory_save = findViewById(R.id.btn_memory_save);
        btn_memory_clear = findViewById(R.id.btn_memory_clear);
        btn_add = findViewById(R.id.btn_add);
        btn_subtract = findViewById(R.id.btn_subtract);
        btn_multiply = findViewById(R.id.btn_multiply);
        btn_divide = findViewById(R.id.btn_divide);
        button_equals = findViewById(R.id.button_equals);
        btn_0 = findViewById(R.id.button_0);
        btn_1 = findViewById(R.id.button_1);
        btn_2 = findViewById(R.id.button_2);
        btn_3 = findViewById(R.id.button_3);
        btn_4 = findViewById(R.id.button_4);
        btn_5 = findViewById(R.id.button_5);
        btn_6 = findViewById(R.id.button_6);
        btn_7 = findViewById(R.id.button_7);
        btn_8 = findViewById(R.id.button_8);
        btn_9 = findViewById(R.id.button_9);
        btn_dot = findViewById(R.id.button_dot);
        inputExpression = findViewById(R.id.inputExpression);
        answer = findViewById(R.id.answer);
        inputExpression.setText("");
        answer.setText("");

        View.OnClickListener numericClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                inputExpression.append(button.getText());
            }
        };

        btn_0.setOnClickListener(numericClickListener);
        btn_1.setOnClickListener(numericClickListener);
        btn_2.setOnClickListener(numericClickListener);
        btn_3.setOnClickListener(numericClickListener);
        btn_4.setOnClickListener(numericClickListener);
        btn_5.setOnClickListener(numericClickListener);
        btn_6.setOnClickListener(numericClickListener);
        btn_7.setOnClickListener(numericClickListener);
        btn_8.setOnClickListener(numericClickListener);
        btn_9.setOnClickListener(numericClickListener);
        btn_add.setOnClickListener(numericClickListener);
        btn_subtract.setOnClickListener(numericClickListener);
        btn_multiply.setOnClickListener(numericClickListener);
        btn_divide.setOnClickListener(numericClickListener);

        View.OnClickListener operatorClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button operatorButton = (Button) v;
                String operator = operatorButton.getText().toString();

                if (inputExpression.getText().length() > 0) {
                    String currentExpression = inputExpression.getText().toString();
                    char lastChar = currentExpression.charAt(currentExpression.length() - 1);

                    if (lastChar == '+' || lastChar == '-' || lastChar == '*' || lastChar == '/') {
                        currentExpression = currentExpression.substring(0, currentExpression.length() - 1);
                    }

                    inputExpression.setText(currentExpression + operator);
                }
            }
        };

        btn_add.setOnClickListener(operatorClickListener);
        btn_subtract.setOnClickListener(operatorClickListener);
        btn_multiply.setOnClickListener(operatorClickListener);
        btn_divide.setOnClickListener(operatorClickListener);

        Button btn_dot = findViewById(R.id.button_dot);
        btn_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputExpression.append(".");
            }
        });


        button_equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String expression = inputExpression.getText().toString();
                    double result = evaluateExpression(expression);
                    answer.setText(String.valueOf(result));
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Invalid expression", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_memory_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double savedValue = Double.parseDouble(answer.getText().toString());
                    memoryValue = String.valueOf(savedValue);
                    Toast.makeText(getApplicationContext(), "Value saved: " + savedValue, Toast.LENGTH_SHORT).show();
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid value. Cannot save to memory.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_memory_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double savedValue = Double.parseDouble(memoryValue);
                    inputExpression.setText("");
                    answer.setText("");
                    inputExpression.append(String.valueOf(savedValue));
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid memory value. Cannot read.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btn_memory_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memoryValue = "";
                Toast.makeText(getApplicationContext(), "Memory cleared", Toast.LENGTH_SHORT).show();
            }
        });


        Button btn_clear = findViewById(R.id.btn_clear);
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputExpression.setText("");
            }
        });
    }

    private double evaluateExpression(String expression) {
        try {
            return evaluate(expression);
        } catch (Exception e) {
            e.printStackTrace();
            return Double.NaN;
        }
    }

    private double evaluate(String expression) throws Exception {
        expression = expression.replaceAll("\\s", "");
        char[] tokens = expression.toCharArray();

        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
             if (Character.isDigit(tokens[i])) {
                StringBuilder sb = new StringBuilder();
                while (i < tokens.length && (Character.isDigit(tokens[i]) || tokens[i] == '.')) {
                    sb.append(tokens[i]);
                    i++;
                }
                i--;
                values.push(Double.parseDouble(sb.toString()));
            } else if (isOperator(tokens[i])) {
                while (!operators.isEmpty() && hasPrecedence(tokens[i], operators.peek())) {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(tokens[i]);
            }
        }

        while (!operators.isEmpty()) {
            values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }

    private boolean isOperator(char token) {
        return token == '+' || token == '-' || token == '*' || token == '/';
    }

    private int getPrecedence(char operator) {
        if (operator == '+' || operator == '-') {
            return 1;
        } else if (operator == '*' || operator == '/') {
            return 2;
        }
        return 0;
    }

    private boolean hasPrecedence(char operator1, char operator2) {
        int precedence1 = getPrecedence(operator1);
        int precedence2 = getPrecedence(operator2);
        return (precedence1 >= precedence2);
    }

    private double applyOperator(char operator, double b, double a) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return a / b;
        }
        return 0;
    }

}
