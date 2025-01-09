package uk.co.mruoc.day16;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import uk.co.mruoc.Maze;
import uk.co.mruoc.Point;

class ReindeerTest {

    private final Reindeer reindeer = new Reindeer();

    @Test
    void shouldReturnEmptyResultIfEndOfMazeNotFound() {
        Maze maze = mock(Maze.class);
        when(maze.getStart()).thenReturn(new Point(0, 0));

        Optional<Result> result = reindeer.search(maze);

        assertThat(result).isEmpty();
    }
}
