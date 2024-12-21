package uk.co.mruoc.day14;

import java.util.Arrays;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.Point;
import uk.co.mruoc.file.FileLoader;

@RequiredArgsConstructor
public class RobotLoader {

    private final RestroomMap map;

    public Robots load(String path) {
        Collection<String> lines = FileLoader.loadContentLinesFromClasspath(path);
        return toRobots(lines);
    }

    private Robots toRobots(Collection<String> lines) {
        return new Robots(lines.stream().map(this::toRobot).toList());
    }

    private Robot toRobot(String line) {
        String[] parts = line.split(" ");
        return Robot.builder()
                .map(map)
                .location(toPoint(parts[0]))
                .velocity(toPoint(parts[1]))
                .build();
    }

    private static Point toPoint(String input) {
        int[] ints = toInts(input);
        return new Point(ints[1], ints[0]);
    }

    private static int[] toInts(String input) {
        String[] parts = input.split("=")[1].split(",");
        return Arrays.stream(parts).mapToInt(Integer::parseInt).toArray();
    }
}
