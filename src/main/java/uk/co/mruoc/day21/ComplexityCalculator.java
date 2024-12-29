package uk.co.mruoc.day21;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.map.UnmodifiableMap;
import uk.co.mruoc.Point;

@RequiredArgsConstructor
public class ComplexityCalculator {

    private final Map<Character, Point> positions;
    private final Collection<String> possibleCommands;

    public ComplexityCalculator() {
        this(buildButtonPositions(), buildCommands());
    }

    public long solve(Collection<String> inputCommands, int depth) {
        Map<String, Long> buttonCosts = possibleCommands.stream()
                .collect(Collectors.toMap(Function.identity(), command -> (long) command.length()));
        for (int i = 0; i < depth; i++) {
            buttonCosts = calculateCostsForNextLayer(buttonCosts, possibleCommands);
        }

        Map<String, Long> outputButtonCosts = calculateCostsForNextLayer(buttonCosts, inputCommands);
        return outputButtonCosts.entrySet().stream()
                .mapToLong(entry -> multiply(entry.getKey(), entry.getValue()))
                .sum();
    }

    private Map<String, Long> calculateCostsForNextLayer(
            Map<String, Long> previousLayerButtonCosts, Collection<String> nextCommands) {
        Map<String, Long> commandCosts = new LinkedHashMap<>();
        for (String command : nextCommands) {
            long cost = 0;
            Point prev = new Point(0, 0);
            for (char button : command.toCharArray()) {
                Point current = positions.get(button);
                Point previous = prev;
                cost += calculateCost(current, previous, previousLayerButtonCosts);
                prev = current;
            }
            commandCosts.put(command, cost);
        }
        return UnmodifiableMap.unmodifiableMap(commandCosts);
    }

    private static long multiply(String command, long multiplicand) {
        long multiplier = Long.parseLong(command.substring(0, 3));
        return multiplicand * multiplier;
    }

    private static long calculateCost(Point current, Point previous, Map<String, Long> previousLayerButtonCosts) {
        Point vector = previous.getVectorTo(current);
        long previousLayerCost = toPreviousLayerCost(current, previous, vector, previousLayerButtonCosts);
        int repeatPressCost = toRepeatPressCost(vector);
        return previousLayerCost + repeatPressCost;
    }

    private static long toPreviousLayerCost(
            Point current, Point previous, Point vector, Map<String, Long> previousLayerButtonCosts) {
        String horizontalChange = toHorizontalChange(vector);
        String verticalChange = toVerticalChange(vector);
        Collection<String> results = new HashSet<>();
        if (!(previous.x == -2L && current.y == 0L)) {
            results.add(verticalChange + horizontalChange + "A");
        }
        if (!(previous.y == 0L && current.x == -2L)) {
            results.add(horizontalChange + verticalChange + "A");
        }
        return results.stream().mapToLong(previousLayerButtonCosts::get).min().orElse(0);
    }

    private static String toHorizontalChange(Point vector) {
        if (vector.x > 0) {
            return ">";
        } else if (vector.x < 0) {
            return "<";
        } else {
            return "";
        }
    }

    private static String toVerticalChange(Point vector) {
        if (vector.y > 0) {
            return "^";
        } else if (vector.y < 0) {
            return "v";
        } else {
            return "";
        }
    }

    private static int toRepeatPressCost(Point vector) {
        return absoluteValueMinusOneOrZero(vector.x) + absoluteValueMinusOneOrZero(vector.y);
    }

    private static int absoluteValueMinusOneOrZero(int value) {
        return Math.max(Math.abs(value) - 1, 0);
    }

    private static Map<Character, Point> buildButtonPositions() {
        Map<Character, Point> map = new HashMap<>();
        map.put('A', new Point(0, 0));
        map.put('0', new Point(0, -1));
        map.put('1', new Point(1, -2));
        map.put('2', new Point(1, -1));
        map.put('3', new Point(1, 0));
        map.put('4', new Point(2, -2));
        map.put('5', new Point(2, -1));
        map.put('6', new Point(2, 0));
        map.put('7', new Point(3, -2));
        map.put('8', new Point(3, -1));
        map.put('9', new Point(3, 0));
        map.put('<', new Point(-1, -2));
        map.put('^', new Point(0, -1));
        map.put('>', new Point(-1, 0));
        map.put('v', new Point(-1, -1));
        return UnmodifiableMap.unmodifiableMap(map);
    }

    private static Collection<String> buildCommands() {
        return List.of("A", ">A", "<A", "^A", "vA", ">vA", "v>A", ">^A", "^>A", "<vA", "v<A", "<^A", "^<A");
    }
}
