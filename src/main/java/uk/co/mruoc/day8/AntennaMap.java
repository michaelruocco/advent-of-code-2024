package uk.co.mruoc.day8;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AntennaMap {

    private static final char AVAILABLE = '.';
    private static final char ANTI_NODE = '#';

    private final char[][] tokens;
    private final AntiNodeFinder antiNodeFinder;

    public long countAntiNodes() {
        return getAntiNodes().count();
    }

    public void populateAntiNodes() {
        getAntiNodes().forEach(this::populateAntiNodeIfAvailable);
    }

    private Stream<Location> getAntiNodes() {
        return getFrequencies().stream()
                .map(Frequency::toAntiNodes)
                .flatMap(Collection::stream)
                .distinct();
    }

    public String getState() {
        return IntStream.range(0, tokens.length)
                .mapToObj(this::formatRow)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String formatRow(int y) {
        return IntStream.range(0, tokens[y].length)
                .mapToObj(x -> tokens[y][x])
                .map(c -> Character.toString(c))
                .collect(Collectors.joining());
    }

    private Collection<Frequency> getFrequencies() {
        int size = tokens.length;
        Map<Character, Frequency> frequencies = new HashMap<>();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                char token = tokens[y][x];
                if (token != AVAILABLE) {
                    Frequency frequency = frequencies.getOrDefault(token, new Frequency(antiNodeFinder, size));
                    frequency.add(new Location(y, x));
                    frequencies.put(token, frequency);
                }
            }
        }
        return frequencies.values();
    }

    private void populateAntiNodeIfAvailable(Location antiNode) {
        if (tokens[antiNode.y][antiNode.x] == AVAILABLE) {
            tokens[antiNode.y][antiNode.x] = ANTI_NODE;
        }
    }
}
