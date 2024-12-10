package uk.co.mruoc.day10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TrailFinder {

    private final TrailMap map;

    private Point location;
    private int height;
    private List<Move> route;
    private Deque<Move> alternatives;
    private Collection<Point> summits;
    private Collection<String> decisions;

    public long findScore() {
        return map.getTrailheads().stream().mapToLong(this::findScore).sum();
    }

    private long findScore(Point trailhead) {
        resetTrail();
        findTrailFrom(trailhead);
        while (!alternatives.isEmpty()) {
            findTrailFrom(alternatives.pop().from);
        }
        return summits.size();
    }

    private void findTrailFrom(Point location) {
        setLocation(location);
        Collection<Move> moves = findCandidateMoves();
        while (!moves.isEmpty()) {
            performMove(moves);
            moves = findCandidateMoves();
        }
        //printMoves();
        recordIfAtSummit();
    }

    private void resetTrail() {
        route = new ArrayList<>();
        alternatives = new LinkedList<>();
        summits = new HashSet<>();
        decisions = new HashSet<>();
    }

    private void setLocation(Point point) {
        location = point;
        height = map.getHeight(location);
    }

    private Collection<Move> findCandidateMoves() {
        Collection<Move> candidates = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            Move move = new Move(location, direction.move(location), direction);
            if (canMakeMove(move)) {
                candidates.add(move);
            }
        }
        return candidates;
    }

    private boolean canMakeMove(Move move) {
        return map.isInBounds(move.to) && isOneLevelHigher(move.to) && !decisions.contains(move.key());
    }

    private boolean isOneLevelHigher(Point candidate) {
        int newHeight = map.getHeight(candidate);
        return height + 1 == newHeight;
    }

    private void performMove(Collection<Move> candidates) {
        Move move = decideMove(candidates);
        location = move.to;
        height = map.getHeight(location);
        route.add(move);
    }

    private Move decideMove(Collection<Move> candidates) {
        Move candidate = candidates.stream().findFirst().orElseThrow();
        if (candidates.size() == 1) {
            return candidate;
        }
        decisions.add(candidate.key());
        candidates.remove(candidate);
        alternatives.addAll(candidates);
        return candidate;
    }

    private void recordIfAtSummit() {
        if (map.isSummit(location)) {
            summits.add(location);
        }
    }

    private void printMoves() {
        System.out.println(BoardFormatter.toString(map, route));
        System.out.println();
    }
}
