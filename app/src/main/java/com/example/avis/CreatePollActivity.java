package com.example.avis;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class CreatePollActivity extends AppCompatActivity {
    private EditText questionEditText;
    private EditText option1EditText;
    private EditText option2EditText;
    private Button createPollButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_poll_activity);

        questionEditText = findViewById(R.id.poll_question_edittext);
        option1EditText = findViewById(R.id.option1_edittext);
        option2EditText = findViewById(R.id.option2_edittext);
        createPollButton = findViewById(R.id.create_poll_button);

        createPollButton.setOnClickListener(v -> {
            String question = questionEditText.getText().toString();
            String option1 = option1EditText.getText().toString();
            String option2 = option2EditText.getText().toString();

            List<String> options = List.of(option1, option2);

            // TODO Send poll creation request to backend API

        });
    }
}

