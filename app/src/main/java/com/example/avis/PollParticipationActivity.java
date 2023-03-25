package com.example.avis;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.avis.model.Poll;

public class PollParticipationActivity extends AppCompatActivity {
    private TextView questionTextView;
    private RadioGroup optionsRadioGroup;
    private Button voteButton;

    private Poll poll;
    private String deviceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poll_participation_activity);

        questionTextView = findViewById(R.id.poll_question_text_view);
        optionsRadioGroup = findViewById(R.id.options_radio_group);
        voteButton = findViewById(R.id.vote_button);

        int pollId = getIntent().getIntExtra("poll_id", -1);
        deviceId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        // TODO Fetch poll details from backend API using the poll ID
        poll = PollRepository.POLLS.get(1);


        questionTextView.setText(poll.getQuestion());

        for (String option : poll.getOptions()) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(option);
            optionsRadioGroup.addView(radioButton);
        }

        voteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedOptionId = optionsRadioGroup.getCheckedRadioButtonId();

                if (selectedOptionId == -1) {
                    // No option selected
                    return;
                }

                RadioButton selectedRadioButton = findViewById(selectedOptionId);
                String selectedOption = selectedRadioButton.getText().toString();

                // TODO Send vote request to backend API
                // ...

                // Store the poll ID and device ID in SharedPreferences to prevent voting more than once
                SharedPreferences sharedPreferences = getSharedPreferences("polls", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(poll.getId() + "_" + deviceId, true);
                editor.apply();

                // Finish the activity
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = getSharedPreferences("polls", MODE_PRIVATE);
        boolean alreadyVoted = sharedPreferences.getBoolean(poll.getId() + "_" + deviceId, false);

        if (alreadyVoted) {
            // User has already voted in this poll, so finish the activity
            finish();
        }
    }
}
