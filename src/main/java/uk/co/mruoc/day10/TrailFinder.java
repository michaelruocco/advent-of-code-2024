package uk.co.mruoc.day10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TrailFinder {

    private final TrailMap map;
    private final Decisions decisions;

    private Point location;
    private int height;
    private List<Move> moves;

    public TrailFinder(TrailMap map) {
        this(map, new Decisions());
    }

    public long findScore() {
        reset();
        return map.getTrailheads().stream().mapToLong(this::findScore).sum();
    }

    private void reset() {
        decisions.reset();
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

    private void printMoves() {
        int[][] m = new int[map.size()][map.size()];
        for (int i = 0; i < moves.size(); i++) {
            Move move = moves.get(i);
            if (i == 0) {
                Point from = move.from;
                m[from.y][from.x] = map.getHeight(from);
            }
            Point to = move.to;
            m[to.y][to.x] = map.getHeight(to);
        }
        Collection<String> lines = new ArrayList<>();
        for (int[] ints : m) {
            StringBuilder row = new StringBuilder();
            for (int x = 0; x < m.length; x++) {
                row.append(ints[x]);
            }
            lines.add(row.toString());
        }
        String grid = lines.stream().collect(Collectors.joining(System.lineSeparator()));
        System.out.println(grid);
        System.out.println();
    }

    private void resetTrail(Point trailhead) {
        location = trailhead;
        height = map.getHeight(trailhead);
        moves = new ArrayList<>();
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
        return map.isInBounds(move.to) && isOneLevelHigher(move.to) && !decisions.alreadyMade(move);
    }

    private boolean isOneLevelHigher(Point candidate) {
        int newHeight = map.getHeight(candidate);
        return height + 1 == newHeight;
    }

    private void performMove(Collection<Move> candidates) {
        Move move = decideMove(candidates);
        location = move.to;
        height = map.getHeight(location);
        moves.add(move);
    }

    private Move decideMove(Collection<Move> candidates) {
        Move candidate = candidates.stream().findFirst().orElseThrow();
        if (candidates.size() == 1) {
            return candidate;
        }
        decisions.add(candidate);
        return candidate;
    }

    private boolean isAtSummit() {
        return map.isSummit(location);
    }
}
