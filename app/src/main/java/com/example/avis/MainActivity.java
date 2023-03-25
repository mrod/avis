package com.example.avis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button mListPollsButton;
    private Button mCreatePollButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // Initialize buttons
        mListPollsButton = findViewById(R.id.list_polls_button);
        mCreatePollButton = findViewById(R.id.create_poll_button);

        // Set click listeners for buttons
        mListPollsButton.setOnClickListener(v -> {
            // Launch ListPollsActivity
            Intent intent = new Intent(MainActivity.this, ListPollsActivity.class);
            startActivity(intent);
        });

        mCreatePollButton.setOnClickListener(v -> {
            // Launch CreatePollActivity
            Intent intent = new Intent(MainActivity.this, CreatePollActivity.class);
            startActivity(intent);
        });
    }
}


