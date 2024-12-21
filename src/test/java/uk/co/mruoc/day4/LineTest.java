package uk.co.mruoc.day4;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import org.junit.jupiter.api.Test;
import uk.co.mruoc.Point;

class LineTest {

    @Test
    void shouldFindCenterPointOfWordsInDiagonalLine() {
        Line line = new Line(
                new Square('M', new Point(0, 0)),
                new Square('A', new Point(1, 1)),
                new Square('S', new Point(2, 2)),
                new Square('M', new Point(3, 3)),
                new Square('A', new Point(4, 4)),
                new Square('S', new Point(5, 5)));

        Collection<Point> points = line.findCenterPoints("MAS");

        assertThat(points).containsExactly(new Point(1, 1), new Point(4, 4));
    }
}
