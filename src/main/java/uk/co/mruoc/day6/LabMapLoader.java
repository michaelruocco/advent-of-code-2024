package uk.co.mruoc.day6;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import uk.co.mruoc.file.FileLoader;

public class LabMapLoader {

    public LabMap loadMap(String path) {
        Collection<String> lines = FileLoader.loadContentLinesFromClasspath(path);
        return toLabMap(new ArrayList<>(lines));
    }

    private static LabMap toLabMap(List<String> lines) {
        int size = lines.size();
        Collection<Location> locations = toLocations(lines, size);
        return LabMap.builder()
                .size(size)
                .guard(new Guard(toGuardLocation(locations)))
                .locations(toMap(locations))
                .build();
    }

    private static Collection<Location> toLocations(List<String> lines, int size) {
        return IntStream.range(0, size)
                .mapToObj(y -> toRow(lines.get(y), y))
                .flatMap(Collection::stream)
                .toList();
    }

    private static Collection<Location> toRow(String line, int y) {
        return IntStream.range(0, line.length())
                .mapToObj(x -> new Location(new Point(x, y), line.charAt(x)))
                .toList();
    }

    private static Location toGuardLocation(Collection<Location> locations) {
        return locations.stream()
                .filter(location -> Guard.isGuardToken(location.getToken()))
                .findFirst()
                .orElseThrow();
    }

    private static Map<String, Location> toMap(Collection<Location> locations) {
        return locations.stream().collect(Collectors.toMap(Location::getKey, Function.identity()));
    }
}
