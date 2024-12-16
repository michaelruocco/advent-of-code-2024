package uk.co.mruoc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.co.mruoc.file.FileLoader;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GridLoader {

    public static char[][] load(String path) {
        Collection<String> lines = FileLoader.loadContentLinesFromClasspath(path);
        return toTokens(new ArrayList<>(lines));
    }

    public static char[][] toTokens(List<String> lines) {
        int size = lines.size();
        char[][] tokens = new char[size][size];
        IntStream.range(0, size).forEach(y -> tokens[y] = toRow(lines.get(y)));
        return tokens;
    }

    private static char[] toRow(String line) {
        return line.toCharArray();
    }
}
