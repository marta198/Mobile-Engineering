package lv.marta.connectedactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView text = findViewById(R.id.textView);
        Button btn3 = findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String returnedText = getInput(SecondActivity.this);
                if (!returnedText.isEmpty()) {
                    text.setText(returnedText);
                } else {
                    Toast toast = Toast.makeText(SecondActivity.this, "Nothing found", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });

        Button btn4 = findViewById(R.id.button4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public static String getInput(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("savedPreferences", 0);
        return prefs.getString("input", "");

    };
}