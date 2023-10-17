package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_memory_read, btn_memory_save, btn_memory_clear, btn_multiply, btn_add, btn_subtract, btn_divide;
    EditText numberInput1, numberInput2;
    TextView answer;
    int memoryValue = 0;
    int tempMemory = 0;

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
        numberInput1 = findViewById(R.id.numberInput1);
        numberInput2 = findViewById(R.id.numberInput2);
        answer = findViewById(R.id.answer);

        btn_memory_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberInput1.setText(String.valueOf(memoryValue));
            }
        });

        btn_memory_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memoryValue = tempMemory;
                showToast("Memory saved: " + memoryValue);
            }
        });

        btn_memory_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memoryValue = 0;
                showToast("Memory Cleared");
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1 = getIntFromEditText(numberInput1);
                int num2 = getIntFromEditText(numberInput2);
                tempMemory = num1 + num2;
                answer.setText("Answer: " + tempMemory);
            }
        });

        btn_subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1 = getIntFromEditText(numberInput1);
                int num2 = getIntFromEditText(numberInput2);
                tempMemory = num1 - num2;
                answer.setText("Answer: " + tempMemory);
            }
        });

        btn_multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1 = getIntFromEditText(numberInput1);
                int num2 = getIntFromEditText(numberInput2);
                tempMemory = num1 * num2;
                answer.setText("Answer: " + tempMemory);
            }
        });

        btn_divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1 = getIntFromEditText(numberInput1);
                int num2 = getIntFromEditText(numberInput2);
                if (num2 == 0) {
                    showToast("Cannot divide by zero");
                } else {
                    tempMemory = num1 / num2;
                    answer.setText("Answer: " + tempMemory);
                }
            }
        });
    }

    public int getIntFromEditText(EditText inputText) {
        if (inputText.getText().toString().equals("")) {
            answer.setText("Please enter a number");
            return 0;
        } else {
            return Integer.parseInt(inputText.getText().toString());
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
