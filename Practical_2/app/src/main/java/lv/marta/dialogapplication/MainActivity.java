package lv.marta.dialogapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
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

        Button btn1 = findViewById(R.id.button1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSecond();
            }
        });

        Button btn2 = findViewById(R.id.button2);

        final String[] listItems = new String[]{"Marta Balode", "Gints Kristaps Pērkons", "Ričards Reinsons"};
        final boolean[] checkedItems = new boolean[listItems.length];

        final List<String> selectedItems = Arrays.asList(listItems);

        btn2.setOnClickListener(v -> {
            AlertDialog.Builder dialogWindow = new AlertDialog.Builder(
                    MainActivity.this);
            dialogWindow.setPositiveButton("OK", null);
            dialogWindow.setNegativeButton("CLOSE", (dialog, which) -> {
                CharSequence text = "You closed the dialog";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(this, text, duration);
                toast.show();
            });;
            dialogWindow.setTitle("Choose group members");

            dialogWindow.setMultiChoiceItems(listItems, checkedItems, (dialog, which, isChecked) -> {
                String currentItem = selectedItems.get(which);
                if (isChecked) {
                    Toast.makeText(MainActivity.this, currentItem + " checked", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, currentItem + " unchecked", Toast.LENGTH_SHORT).show();
                }
            });

            final AlertDialog alertDialog = dialogWindow.create();
            alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {

                @Override
                public void onShow(DialogInterface dialog) {
                    Button b = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    b.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            CharSequence text = "You clicked OK";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(MainActivity.this, text, duration);
                            toast.show();
                        }
                    });
                }
            });

            alertDialog.show();
        });
    }

    public void goToSecond() {
        Intent intent = new Intent(MainActivity.this, secondScreen.class);
        startActivity(intent);
    };
};