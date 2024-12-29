package uk.co.mruoc.day19;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import uk.co.mruoc.file.FileLoader;

public class TowelPatternLoader {

    public TowelPatterns loadTowelPatterns(String path) {
        Collection<String> lines = FileLoader.loadContentLinesFromClasspath(path);
        return toTowelPatterns(toTowelPatternsLine(lines));
    }

    public Collection<String> loadTargetDesigns(String path) {
        Collection<String> lines = FileLoader.loadContentLinesFromClasspath(path);
        return toTargetDesigns(lines);
    }

    private static String toTowelPatternsLine(Collection<String> inputLines) {
        return inputLines.stream().findFirst().orElseThrow();
    }

    private static TowelPatterns toTowelPatterns(String line) {
        return new TowelPatterns(line.split(", "));
    }

    private static Collection<String> toTargetDesigns(Collection<String> inputLines) {
        List<String> lines = new ArrayList<>(inputLines);
        long splitIndex = lines.indexOf("");
        return lines.stream().skip(splitIndex + 1).toList();
    }
}
