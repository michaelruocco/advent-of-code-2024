package uk.co.mruoc.day6;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;
import uk.co.mruoc.file.FileLoader;

public class LabMapLoader {

    public LabMap loadMap(String path) {
        Collection<String> lines = FileLoader.loadContentLinesFromClasspath(path);
        return toLabMap(new ArrayList<>(lines));
    }

    private static LabMap toLabMap(List<String> lines) {
        return new LabMap(toTokens(lines));
    }

    private static char[][] toTokens(List<String> lines) {
        int size = lines.size();
        char[][] tokens = new char[size][size];
        IntStream.range(0, size).forEach(y -> tokens[y] = toRow(lines.get(y)));
        return tokens;
    }

    private static char[] toRow(String line) {
        return line.toCharArray();
    }
}
