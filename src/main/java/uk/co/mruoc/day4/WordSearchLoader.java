package uk.co.mruoc.day4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
                .mapToObj(x -> new Square(s.charAt(x), new Point(x, y)))
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
                .mapToObj(y -> new Square(s.charAt(y), new Point(x, y)))
                .toList();
    }

    public static Collection<Line> toTopLeftToBottomRightDiagonals(int width, int height, List<Line> rows) {
        Collection<Line> diagonals = new ArrayList<>();
        List<Square> squares = new ArrayList<>();

        for (int column = 0; column < width; column++) {
            int x = column;
            int y = 0;
            while (x < width && y < rows.size()) {
                squares.add(new Square(rows.get(x).charAt(y), new Point(x, y)));
                x++;
                y++;
            }
            diagonals.add(new Line(squares));
            squares = new ArrayList<>();
        }

        for (int row = 1; row < height; row++) {
            int x = 0;
            int y = row;
            while (x < width && y < height) {
                squares.add(new Square(rows.get(x).charAt(y), new Point(x, y)));
                x++;
                y++;
            }
            diagonals.add(new Line(squares));
            squares = new ArrayList<>();
        }

        return diagonals;
    }

    public static Collection<Line> getTopRightToBottomLeftDiagonals(int width, int height, List<Line> rows) {
        Collection<Line> diagonals = new ArrayList<>();
        List<Square> squares = new ArrayList<>();

        for (int column = width - 1; column >= 0; column--) {
            int x = column;
            int y = 0;
            while (x >= 0 && y < height) {
                squares.add(new Square(rows.get(x).charAt(y), new Point(x, y)));
                x--;
                y++;
            }
            diagonals.add(new Line(squares));
            squares = new ArrayList<>();
        }

        for (int row = 1; row < height; row++) {
            int x = width - 1;
            int y = row;
            while (x >= 0 && y < height) {
                squares.add(new Square(rows.get(x).charAt(y), new Point(x, y)));
                x--;
                y++;
            }
            diagonals.add(new Line(squares));
            squares = new ArrayList<>();
        }

        return diagonals;
    }
}
