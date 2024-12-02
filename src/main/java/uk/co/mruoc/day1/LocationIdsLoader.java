package uk.co.mruoc.day1;

import java.util.Collection;
import uk.co.mruoc.file.FileLoader;

public class LocationIdsLoader {
    public LocationIds load(String path) {
        Collection<String> lines = FileLoader.loadContentLinesFromClasspath(path);
        return LocationIds.builder()
                .one(lines.stream().map(line -> toId(line, 0)).toList())
                .two(lines.stream().map(line -> toId(line, 1)).toList())
                .build();
    }

    private static Integer toId(String line, int index) {
        String[] parts = line.split("\\s+");
        return Integer.parseInt(parts[index]);
    }
}
