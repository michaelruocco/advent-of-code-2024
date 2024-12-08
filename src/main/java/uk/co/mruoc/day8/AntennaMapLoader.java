package uk.co.mruoc.day8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.file.FileLoader;

@RequiredArgsConstructor
public class AntennaMapLoader {

    private final AntiNodeFinder antiNodeFinder;

    public AntennaMap load(String path) {
        Collection<String> lines = FileLoader.loadContentLinesFromClasspath(path);
        return toAntennaMap(new ArrayList<>(lines));
    }

    private AntennaMap toAntennaMap(List<String> lines) {
        return new AntennaMap(toTokens(lines), antiNodeFinder);
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
