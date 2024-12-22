package uk.co.mruoc.day16;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.Direction;

@RequiredArgsConstructor
public class Reindeer {

    private final Maze maze;

    @Getter
    private final Move initialMove;

    public Reindeer(Maze maze) {
        this(maze, new Move(maze.getStart(), Direction.EAST));
    }

    public long findLowestScore() {
        Map<Move, Long> exploredMoves = new HashMap<>();
        Map<Move, Long> nextMoves = new LinkedHashMap<>(Map.of(initialMove, 0L));

        while (!nextMoves.isEmpty() && exploredMoves.keySet().stream().noneMatch(move -> maze.endsAt(move.location))) {
            Map.Entry<Move, Long> entry = nextMoves.entrySet().stream()
                    .min(Map.Entry.comparingByValue())
                    .orElseThrow();
            Move nextMove = entry.getKey();
            long score = entry.getValue();
            exploredMoves.put(nextMove, score);
            nextMoves.remove(nextMove);

            Stream.of(
                            new ScoredMove(
                                    nextMove.direction.move(nextMove.location), nextMove.direction, score + 1),
                            new ScoredMove(
                                    nextMove.location, nextMove.direction.rotateClockwise(), score + 1000),
                            new ScoredMove(
                                    nextMove.location, nextMove.direction.rotateAntiClockwise(), score + 1000))
                    .filter(scoredMove ->
                            maze.pathAt(scoredMove.move.location) && !exploredMoves.containsKey(scoredMove.move))
                    .forEach(scoredMove -> nextMoves.put(scoredMove.move, scoredMove.score));
        }

        return exploredMoves.entrySet().stream()
                .filter(e -> maze.endsAt(e.getKey().location))
                .mapToLong(Map.Entry::getValue)
                .min()
                .orElseThrow();
    }
}
