package uk.co.mruoc.day10;

import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Decisions {

    private final Map<String, Move> history;

    public Decisions() {
        this(new HashMap<>());
    }

    public void reset() {
        history.clear();
    }

    public boolean alreadyMade(Move move) {
        return history.containsKey(move.key());
    }

    public void add(Move move) {
        history.put(move.key(), move);
    }
}
