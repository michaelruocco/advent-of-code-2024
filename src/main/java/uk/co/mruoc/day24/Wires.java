package uk.co.mruoc.day24;

import static java.util.Comparator.reverseOrder;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Wires {

    private final Map<String, Integer> values;

    public void set(String wire, int value) {
        values.put(wire, value);
    }

    public Integer get(String wire) {
        return values.get(wire);
    }

    public long getDecimalValue() {
        return toDecimal(getBinaryValue());
    }

    public String getFinalBit(char c) {
        return getWires(c).findFirst().orElseThrow();
    }

    private String getBinaryValue() {
        return getBinaryNumberWires().stream()
                .map(wire -> Integer.toString(values.get(wire)))
                .collect(Collectors.joining());
    }

    private Collection<String> getBinaryNumberWires() {
        return getWires('z').toList();
    }

    private Stream<String> getWires(char c) {
        return values.keySet().stream().filter(key -> key.charAt(0) == c).sorted(reverseOrder());
    }

    private static long toDecimal(String binaryNumber) {
        return Long.parseLong(binaryNumber, 2);
    }
}
