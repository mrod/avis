package com.example.avis.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Poll implements Serializable {
    private int id;
    private String question;
    private List<String> options;

    public Poll(int id, String question, List<String> options) {
        this.id = id;
        this.question = question;
        this.options = options;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Poll poll = (Poll) o;
        return id == poll.id && Objects.equals(question, poll.question) && Objects.equals(options, poll.options);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, options);
    }

    @Override
    public String toString() {
        return "Poll{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", options=" + options +
                '}';
    }
}
