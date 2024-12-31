package uk.co.mruoc.day1;

import java.util.Collection;
import uk.co.mruoc.file.FileLoader;

public class LocationIdsLoader {
    public LocationIds load(String path) {
        Collection<String[]> allParts = FileLoader.loadContentLinesFromClasspath(path).stream()
                .map(line -> line.split("\\s+"))
                .toList();
        return LocationIds.builder()
                .one(allParts.stream().map(parts -> toId(parts, 0)).toList())
                .two(allParts.stream().map(parts -> toId(parts, 1)).toList())
                .build();
    }

    private static Integer toId(String[] parts, int index) {
        return Integer.parseInt(parts[index]);
    }
}
