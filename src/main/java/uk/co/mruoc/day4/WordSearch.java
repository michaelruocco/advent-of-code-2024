package uk.co.mruoc.day4;

import java.util.Collection;
import lombok.Builder;
import org.apache.commons.collections4.CollectionUtils;
import uk.co.mruoc.Point;

@Builder
public class WordSearch {

    private final Collection<Line> rows;
    private final Collection<Line> columns;
    private final Collection<Line> topLeftBottomRightDiagonals;
    private final Collection<Line> topRightToBottomLeftDiagonals;

    public int findAll(String word) {
        return findAll(word, rows)
                + findAll(word, columns)
                + findAll(word, topLeftBottomRightDiagonals)
                + findAll(word, topRightToBottomLeftDiagonals);
    }

    public int findAllInX(String word) {
        Collection<Point> points1 = findCenterPoints(word, topLeftBottomRightDiagonals);
        Collection<Point> points2 = findCenterPoints(word, topRightToBottomLeftDiagonals);
        Collection<Point> points = CollectionUtils.intersection(points1, points2);
        return points.size();
    }

    private int findAll(String word, Collection<Line> lines) {
        return lines.stream().mapToInt(line -> line.findAll(word)).sum();
    }

    private static Collection<Point> findCenterPoints(String word, Collection<Line> lines) {
        return lines.stream()
                .map(line -> line.findCenterPoints(word))
                .flatMap(Collection::stream)
                .toList();
    }
}
