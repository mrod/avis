package com.example.avis;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.avis.model.Poll;

import java.util.ArrayList;
import java.util.List;

public class ListPollsActivity extends AppCompatActivity {

    private RecyclerView mPollsRecyclerView;
    private PollAdapter mPollAdapter;
    private List<Poll> mPolls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_polls_activity);

        // Set up the RecyclerView and its adapter
        mPollsRecyclerView = findViewById(R.id.polls_recycler_view);
        mPollsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPolls = new ArrayList<>();
        mPollAdapter = new PollAdapter(mPolls);
        mPollsRecyclerView.setAdapter(mPollAdapter);

        // Load the polls from a data source (e.g. a database or web API)
        loadPolls();
    }

    private void loadPolls() {
        // TODO: Load the polls from a data source and add them to the mPolls list
        mPolls.addAll(PollRepository.POLLS);
        mPollAdapter.notifyDataSetChanged();
    }

    private class PollHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mQuestionTextView;
        private Poll mPoll;

        public PollHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_poll, parent, false));
            itemView.setOnClickListener(this);
            mQuestionTextView = itemView.findViewById(R.id.poll_question_text_view);
        }

        public void bind(Poll poll) {
            mPoll = poll;
            mQuestionTextView.setText(mPoll.getQuestion());
        }

        @Override
        public void onClick(View v) {
            // Start the PollParticipationActivity and pass in the selected poll
            Intent intent = new Intent(ListPollsActivity.this, PollParticipationActivity.class);
            intent.putExtra("poll", mPoll);
            startActivity(intent);
        }
    }

    private class PollAdapter extends RecyclerView.Adapter<PollHolder> {

        private List<Poll> mPolls;

        public PollAdapter(List<Poll> polls) {
            mPolls = polls;
        }

        @NonNull
        @Override
        public PollHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(ListPollsActivity.this);
            return new PollHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull PollHolder holder, int position) {
            Poll poll = mPolls.get(position);
            holder.bind(poll);
        }

        @Override
        public int getItemCount() {
            return mPolls.size();
        }
    }
}
