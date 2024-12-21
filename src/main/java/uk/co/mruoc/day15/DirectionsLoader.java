package uk.co.mruoc.day15;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import uk.co.mruoc.Direction;
import uk.co.mruoc.file.FileLoader;

public class DirectionsLoader {

    public Directions load(String path) {
        List<String> lines = new ArrayList<>(FileLoader.loadContentLinesFromClasspath(path));
        return toDirections(toDirectionLines(lines));
    }

    private static List<String> toDirectionLines(Collection<String> inputLines) {
        List<String> lines = new ArrayList<>(inputLines);
        int splitIndex = lines.indexOf("");
        return lines.subList(splitIndex, lines.size());
    }

    private static Directions toDirections(Collection<String> lines) {
        return new Directions(lines.stream()
                .map(DirectionsLoader::toDirections)
                .flatMap(Collection::stream)
                .toList());
    }

    private static Collection<Direction> toDirections(String line) {
        return line.chars().mapToObj(c -> (char) c).map(Direction::build).toList();
    }
}
