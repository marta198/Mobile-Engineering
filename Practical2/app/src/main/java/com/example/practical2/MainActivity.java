package com.example.practical2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button goToSecondButton = findViewById(R.id.button_goto_2nd);
        Button showDialogButton = findViewById(R.id.button_dialog);

        goToSecondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = SecondActivity.newIntent(MainActivity.this);
                startActivity(intent);
            }
        });

        showDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new GroupDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "GroupDialog");
            }
        });
    }

    public static class GroupDialogFragment extends DialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final String[] groupMembers = {"Ričards Reinsons", "Marta Balode", "Gints Kristaps Pērkons"};
            final boolean[] checkedItems = {false, false, false};

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("2nd Group's Dialog")
                    .setMultiChoiceItems(groupMembers, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                            if (isChecked) {
                                Toast.makeText(getActivity(), groupMembers[which] + " checked", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), groupMembers[which] + " undchecked", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .setPositiveButton(R.string.group_dialog_ok_button, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(), R.string.group_dialog_ok, Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton(R.string.group_dialog_close_button, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(), R.string.group_dialog_closed, Toast.LENGTH_SHORT).show();
                        }
                    });

            return builder.create();
        }
    }
}
