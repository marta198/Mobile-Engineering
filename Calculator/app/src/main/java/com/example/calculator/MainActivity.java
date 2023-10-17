package com.example.calculator;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.udojava.evalex.Expression;





public class MainActivity extends AppCompatActivity {
    String screenData = "";
    String savedData = "";
    TextView screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button saveBtn = findViewById(R.id.saveBtn);
        Button readBtn = findViewById(R.id.readBtn);
        Button clearBtn = findViewById(R.id.clearBtn);
        Button oneBtn = findViewById(R.id.oneBtn);
        Button twoBtn = findViewById(R.id.twoBtn);
        Button threeBtn = findViewById(R.id.threeBtn);
        Button fourBtn = findViewById(R.id.fourBtn);
        Button fiveBtn = findViewById(R.id.fiveBtn);
        Button sixBtn = findViewById(R.id.sixBtn);
        Button sevenBtn = findViewById(R.id.sevenBtn);
        Button eightBtn = findViewById(R.id.eightBtn);
        Button nineBtn = findViewById(R.id.nineBtn);
        Button zeroBtn = findViewById(R.id.zeroBtn);
        Button divBtn = findViewById(R.id.divBtn);
        Button multiBtn = findViewById(R.id.multiBtn);
        Button subBtn = findViewById(R.id.subBtn);
        Button addBtn = findViewById(R.id.addBtn);
        Button delBtn = findViewById(R.id.delBtn);
        Button decimalBtn = findViewById(R.id.decimalBtn);
        Button deleteBtn = findViewById(R.id.deleteBtn);
        Button eqlBtn = findViewById(R.id.eqlBtn);
        eqlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateResult();
            }
        });



        screen = findViewById(R.id.screen);
        addListenerTyping(zeroBtn,'0');
        addListenerTyping(oneBtn,'1');
        addListenerTyping(twoBtn,'2');
        addListenerTyping(threeBtn,'3');
        addListenerTyping(fourBtn,'4');
        addListenerTyping(fiveBtn,'5');
        addListenerTyping(sixBtn,'6');
        addListenerTyping(sevenBtn,'7');
        addListenerTyping(eightBtn,'8');
        addListenerTyping(nineBtn,'9');
        addListenerTyping(subBtn,'-');
        addListenerTyping(addBtn,'+');
        addListenerTyping(multiBtn,'*');
        addListenerTyping(divBtn,'/');
        addListenerTyping(decimalBtn,'.');
        addListenerDelete(delBtn,"clear");
        addListenerDelete(deleteBtn,"del");
        clearBtn.setOnClickListener(view -> clearSavedData());
        saveBtn.setOnClickListener(view -> saveSavedData());
        readBtn.setOnClickListener(view -> readSavedData());
        eqlBtn.setOnClickListener(view -> calculateResult());
    }


    void addCharacter(char data) {


        if (screenData.length() <1){
            if (data == '.'){
                screenData += '0';
            }
            screenData += data;
            refreshScreen();
            return;
        }
        String lastChar = screenData.substring(screenData.length()-1);
        if (data == '.'){
            int lastSignIdx = -1;
            List<String> signList = Arrays.asList("+","-","/","*");
            for (String item:signList) {
                lastSignIdx = screenData.lastIndexOf(item);
                if (lastSignIdx >=0){
                    if (!screenData.substring(screenData.length()-1).contains(".")){
                    }
                    break;
                }
            }
            if (lastSignIdx >=0){
                if (!screenData.substring(screenData.length()-1).contains(".")){
                    if (lastSignIdx == screenData.length()-1){
                        screenData += '0';
                    }
                    screenData += data;
                }
            }else {
                if (!screenData.contains(".")){
                    screenData += data;
                }
            }
        } else if ("/*-+".contains(lastChar) && "/*-+".contains(""+data)){
            screenData = screenData.substring(0,screenData.length()-1)+ data;
        }
        else {
            screenData += data;
            Log.d("testData", ""+data);
        }
        refreshScreen();
    }


    void saveSavedData(){
        savedData = screenData;
        Log.d("dataSave", savedData);
    }
    void clearSavedData(){
        savedData = "";
        Log.d("dataSave", savedData);
    }
    void readSavedData(){
        screenData += savedData;
        refreshScreen();
        Log.d("dataSave", savedData);
    }


    void removeCharacter(String data) {
        if (data.equals("del")){
            screenData = "";
        }
        if (data.equals("clear")){
            if (screenData.length() >= 1){
                screenData = screenData.substring(0,screenData.length()-1);
            }
        }
        refreshScreen();
    }

    void refreshScreen() {
        screen.setText(screenData);
    }
    void addListenerTyping(Button btn, char value){
        btn.setOnClickListener(view -> addCharacter(value));
    }
    void addListenerDelete(Button btn, String value){
        btn.setOnClickListener(view -> removeCharacter(value));
    }


    void showToast(String msg){
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(this /* MyActivity */, msg, duration);
        toast.show();
    }




    void calculateResult() {
        Expression expression=new Expression(screenData); //This Library Evaluate It
        try {
            screenData = expression.eval().toString(); //Insert The Data into A String
        }catch (NumberFormatException e){
            showToast(e.getMessage()+"!");
        }catch (ArithmeticException e){
            showToast(e.getMessage()+"!");
        }catch (Exception e){
            showToast(e.getMessage()+"!");
        }

        refreshScreen();
    }



}