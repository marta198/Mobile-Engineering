package com.example.repositoryg_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Context currentContext = this;
        Button readPrefBtn = (Button) findViewById(R.id.readPrefBtn);
        readPrefBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String prefText = getPrefText();
                if (prefText == ""){
                    MainActivity.showToast(currentContext,"Nothing found");
                }
                else {
                    TextView txtView = (TextView) findViewById(R.id.InfoText);
                    txtView.setText(prefText);
                }
            }
        });

        Button backBtn = (Button) findViewById(R.id.BackBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    private String getPrefText() {

        return MainActivity.getText(this);
    }
}

