package lv.group2.calculatorapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9,
            btn_Add, btn_Sub, btn_Mul, btn_Div, btn_equal, btn_dec, btn_delete, btn_mr, btn_ms, btn_mc;
    boolean mAddition, mSubtract, mMultiplication, mDivision ;
    TextView input;
    String text = "";
    float num1, num2;
    float memoryVal = 0;
    float savedCalc = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_0 = findViewById(R.id.btn_0);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 =  findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        btn_Mul = findViewById(R.id.btn_Mul);
        btn_Div = findViewById(R.id.btn_Div);
        btn_Add = findViewById(R.id.btn_Add);
        btn_Sub = findViewById(R.id.btn_Sub);
        btn_dec = findViewById(R.id.btn_dec);
        btn_delete = findViewById(R.id.btn_delete);
        btn_equal = findViewById(R.id.btn_equal);
        btn_mc = findViewById(R.id.btn_mc);
        btn_ms = findViewById(R.id.btn_ms);
        btn_mr = findViewById(R.id.btn_mr);
        input = findViewById(R.id.input);

        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = text + "0";
                input.setText(text);
            }
        });

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = text + "1";
                input.setText(text);
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = text + "2";
                input.setText(text);
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = text + "3";
                input.setText(text);
            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = text + "4";
                input.setText(text);
            }
        });

        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = text + "5";
                input.setText(text);
            }
        });

        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = text + "6";
                input.setText(text);
            }
        });

        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = text + "7";
                input.setText(text);
            }
        });

        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = text + "8";
                input.setText(text);
            }
        });

        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = text + "9";
                input.setText(text);
            }
        });

        btn_dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = text + ".";
                input.setText(text);
            }
        });

        btn_Mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Float.parseFloat(text + "");
                mMultiplication = true ;
                input.setText(null);
                text = "";
            }
        });

        btn_Div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Float.parseFloat(text + "");
                mDivision = true ;
                input.setText(null);
                text = "";
            }
        });

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Float.parseFloat(text + "");
                mAddition = true ;
                input.setText(null);
                text = "";
            }
        });

        btn_Sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Float.parseFloat(text + "");
                mSubtract = true ;
                input.setText(null);
                text = "";
            }
        });

        btn_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num2 = Float.parseFloat(text + "");

                if (mSubtract){
                    savedCalc = num1 - num2;
                    text = String.valueOf(savedCalc);
                    input.setText(text);
                    mSubtract=false;
                } else if (mAddition){
                    savedCalc = num1 + num2;
                    text = String.valueOf(savedCalc);
                    input.setText(text);
                    mAddition=false;
                } else if (mMultiplication){
                    savedCalc = num1 * num2;
                    text = String.valueOf(savedCalc);
                    input.setText(text);
                    mMultiplication=false;
                } else if (mDivision){
                    savedCalc = num1 / num2;
                    text = String.valueOf(savedCalc);
                    input.setText(text);
                    mDivision=false;
                }
            }
        });

        btn_mc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memoryVal = 0;
            }
        });

        btn_mr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = memoryVal;
                text = num1 + "";
                input.setText(text);
            }
        });

        btn_ms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memoryVal = savedCalc;
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (text.length() != 0) {
                        text = text.substring(0, text.length()-1);
                        input.setText(text);
                }

            }
        });

    }

}