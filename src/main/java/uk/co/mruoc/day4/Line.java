package uk.co.mruoc.day4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import uk.co.mruoc.Point;

@RequiredArgsConstructor
public class Line {

    private final List<Square> squares;
    private final String chars;

    public Line(Square... squares) {
        this(List.of(squares));
    }

    public Line(List<Square> squares) {
        this(squares, toString(squares));
    }

    public int getSize() {
        return chars.length();
    }

    public char charAt(int index) {
        return chars.charAt(index);
    }

    public int findAll(String target) {
        int matches = StringUtils.countMatches(chars, target);
        int reverseMatches = StringUtils.countMatches(chars, reverse(target));
        return matches + reverseMatches;
    }

    public Collection<Point> findCenterPoints(String target) {
        String reversed = reverse(target);
        return Stream.concat(toCenterPoints(target), toCenterPoints(reversed))
                .distinct()
                .toList();
    }

    private Stream<Point> toCenterPoints(String target) {
        return findCenterIndexes(target).stream().map(this::toPoint);
    }

    private Collection<Integer> findCenterIndexes(String target) {
        if (!chars.contains(target)) {
            return Collections.emptyList();
        }
        Collection<Integer> indexes = new ArrayList<>();
        int index = chars.indexOf(target);
        while (index >= 0) {
            indexes.add(index + (target.length() / 2));
            index = chars.indexOf(target, index + 1);
        }
        return indexes;
    }

    private Point toPoint(int index) {
        return squares.get(index).point;
    }

    private static String reverse(String word) {
        return new StringBuilder(word).reverse().toString();
    }

    private static String toString(Collection<Square> squares) {
        return squares.stream().map(square -> Character.toString(square.token)).collect(Collectors.joining());
    }
}
