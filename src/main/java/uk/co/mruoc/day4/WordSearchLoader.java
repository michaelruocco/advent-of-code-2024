package uk.co.mruoc.day4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import uk.co.mruoc.Point;
import uk.co.mruoc.file.FileLoader;

public class WordSearchLoader {

    public WordSearch load(String path) {
        List<String> lines = new ArrayList<>(FileLoader.loadContentLinesFromClasspath(path));
        List<Line> rows = toRows(lines);
        int width = rows.stream().findFirst().map(Line::getSize).orElse(0);
        List<Line> columns = toColumns(rows);
        int height = columns.stream().findFirst().map(Line::getSize).orElse(0);
        return WordSearch.builder()
                .rows(rows)
                .columns(columns)
                .topLeftBottomRightDiagonals(toTopLeftToBottomRightDiagonals(width, height, rows))
                .topRightToBottomLeftDiagonals(getTopRightToBottomLeftDiagonals(width, height, rows))
                .build();
    }

    private static List<Line> toRows(List<String> lines) {
        return IntStream.range(0, lines.size())
                .mapToObj(y -> new Line(toRowSquares(lines.get(y), y)))
                .toList();
    }

    private static List<Square> toRowSquares(String s, int y) {
        return IntStream.range(0, s.length())
                .mapToObj(x -> new Square(s.charAt(x), new Point(y, x)))
                .toList();
    }

    private static List<Line> toColumns(List<Line> rows) {
        int width = rows.stream().findFirst().map(Line::getSize).orElse(0);
        return IntStream.range(0, width).mapToObj(x -> toColumn(x, rows)).toList();
    }

    private static Line toColumn(int x, List<Line> rows) {
        return new Line(toColumnSquares(x, rows));
    }

    private static List<Square> toColumnSquares(int x, List<Line> rows) {
        String s = rows.stream().map(row -> Character.toString(row.charAt(x))).collect(Collectors.joining());
        return IntStream.range(0, s.length())
                .mapToObj(y -> new Square(s.charAt(y), new Point(y, x)))
                .toList();
    }

    private static Collection<Line> toTopLeftToBottomRightDiagonals(int width, int height, List<Line> rows) {
        return Stream.concat(
                        toTopHalfTopLeftToBottomRightDiagonals(width, rows),
                        toBottomHalfTopLeftToBottomRightDiagonals(height, rows))
                .toList();
    }

    private static Stream<Line> toTopHalfTopLeftToBottomRightDiagonals(int width, List<Line> rows) {
        return IntStream.range(0, width)
                .mapToObj(column -> toTopHalfTopLeftToBottomRightDiagonalLine(width, column, rows));
    }

    private static Line toTopHalfTopLeftToBottomRightDiagonalLine(int width, int column, List<Line> rows) {
        List<Square> squares = new ArrayList<>();
        for (int x = column, y = 0; x < width; x++, y++) {
            squares.add(new Square(rows.get(x).charAt(y), new Point(y, x)));
        }
        return new Line(squares);
    }

    private static Stream<Line> toBottomHalfTopLeftToBottomRightDiagonals(int height, List<Line> rows) {
        return IntStream.range(1, height)
                .mapToObj(row -> toBottomHalfTopLeftToBottomRightDiagonalLine(height, row, rows));
    }

    private static Line toBottomHalfTopLeftToBottomRightDiagonalLine(int height, int row, List<Line> rows) {
        List<Square> squares = new ArrayList<>();
        for (int x = 0, y = row; y < height; x++, y++) {
            squares.add(new Square(rows.get(x).charAt(y), new Point(y, x)));
        }
        return new Line(squares);
    }

    private static Collection<Line> getTopRightToBottomLeftDiagonals(int width, int height, List<Line> rows) {
        return Stream.concat(
                        toTopHalfTopRightToBottomLeftDiagonals(width, rows),
                        toBottomHalfTopRightToBottomLeftDiagonals(width, height, rows))
                .toList();
    }

    private static Stream<Line> toTopHalfTopRightToBottomLeftDiagonals(int width, List<Line> rows) {
        return IntStream.range(0, width).mapToObj(column -> toTopHalfTopRightToBottomLeftDiagonalLine(column, rows));
    }

    private static Line toTopHalfTopRightToBottomLeftDiagonalLine(int column, List<Line> rows) {
        List<Square> squares = new ArrayList<>();
        for (int x = column, y = 0; x >= 0; x--, y++) {
            squares.add(new Square(rows.get(x).charAt(y), new Point(y, x)));
        }
        return new Line(squares);
    }

    private static Stream<Line> toBottomHalfTopRightToBottomLeftDiagonals(int width, int height, List<Line> rows) {
        return IntStream.range(1, height)
                .mapToObj(row -> toBottomHalfTopRightToBottomLeftDiagonalLine(width, height, row, rows));
    }

    private static Line toBottomHalfTopRightToBottomLeftDiagonalLine(int width, int height, int row, List<Line> rows) {
        List<Square> squares = new ArrayList<>();
        for (int x = width - 1, y = row; y < height; x--, y++) {
            squares.add(new Square(rows.get(x).charAt(y), new Point(y, x)));
        }
        return new Line(squares);
    }
}
