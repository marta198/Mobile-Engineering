package lv.marta.connectedactivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.editText);
        Button btn1 = findViewById(R.id.button1);

        String text = MainActivity.this.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE).toString();
        if (!text.isEmpty()) {
            editText.setText(text);
        };

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserInput(MainActivity.this,editText.getText().toString());
            }
        });

        Button btn2 = findViewById(R.id.button2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSecond();
            }
        });

        Spinner spinnerModes=findViewById(R.id.spinner_modes);
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, R.array.modes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinnerModes.setAdapter(adapter);

        spinnerModes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {
                    String mode = item.toString();
                    if (mode.equals("Default")) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    } else if (mode.equals("Dark")){
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    } else if (mode.equals("Light")) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub

            }
        });


    };

    public void goToSecond() {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    };

    public static void saveUserInput(Context context, String input) {
        SharedPreferences prefs = context.getSharedPreferences("savedPreferences", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("input", input);
        editor.commit();
    };
}