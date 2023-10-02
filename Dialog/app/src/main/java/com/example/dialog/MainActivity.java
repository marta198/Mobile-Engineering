package com.example.dialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button dialogBtn = (Button) findViewById(R.id.dialogBtn);
        dialogBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String text = "testText";
                showAlertDialog("show text");
            }
        });

        Button activity2Btn = (Button)findViewById(R.id.goActivity2);
        activity2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                startActivity(intent);
            }
        });
    }





    private void showToast(String message){
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, message, duration);
        toast.show();
    }




    private void showAlertDialog(String message){
        List<String> dialogValues = Arrays.asList(getResources().getStringArray(R.array.dialogItems));
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("2. Group's Dialog")
                .setMultiChoiceItems(R.array.dialogItems, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {
                        if (isChecked){
                            showToast(dialogValues.get(which)+" checked");
                        }else {
                            showToast(dialogValues.get(which)+" unchecked");
                        }
                    }
                })
                .setPositiveButton("OK", null)
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                        showToast("You closed dialog");
                    }
                }).create();
        dialog.show();

        Button positiveBtn = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showToast("You clicked OK");
            }
        });
    }


}

