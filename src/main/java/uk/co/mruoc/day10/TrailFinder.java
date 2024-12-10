package uk.co.mruoc.day10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TrailFinder {

    private final TrailMap map;

    private Point location;
    private int height;
    private List<Move> route;

    public long findScore() {
        return map.getTrailheads().stream().mapToLong(this::findScore).sum();
    }

    private long findScore(Point trailhead) {
        int score = 0;
        resetTrail(trailhead);
        Collection<Move> moves = findCandidateMoves();
        while (!moves.isEmpty()) {
            performMove(moves);
            moves = findCandidateMoves();
        }
        printMoves();
        if (isAtSummit()) {
            score++;
        }
        return score;
    }

    private void resetTrail(Point trailhead) {
        location = trailhead;
        height = map.getHeight(trailhead);
        route = new ArrayList<>();
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
        return map.isInBounds(move.to) && isOneLevelHigher(move.to);
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
        return candidate;
    }

    private boolean isAtSummit() {
        return map.isSummit(location);
    }

    private void printMoves() {
        System.out.println(BoardFormatter.toString(map, route));
        System.out.println();
    }
}
