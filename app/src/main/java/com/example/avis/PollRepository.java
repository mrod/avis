package com.example.avis;

import com.example.avis.model.Poll;

import java.util.ArrayList;
import java.util.List;

public class PollRepository {

    public static final List<Poll> POLLS = new ArrayList<>(List.of(
            new Poll(1, "Favorite Color?", List.of("Blue", "Red")),
            new Poll(2, "Favorite meal?", List.of("Pizza", "Burger"))
    ));

}
