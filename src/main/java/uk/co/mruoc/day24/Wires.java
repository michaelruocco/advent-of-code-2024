package uk.co.mruoc.day24;

import static java.util.Comparator.reverseOrder;
import static uk.co.mruoc.day24.WireConverter.toWireId;

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

    public long getDecimalValue(char wireId) {
        return toDecimal(getBinaryValue(wireId));
    }

    private String getBinaryValue(char wireId) {
        return getBinaryNumberWires(wireId).stream()
                .map(wire -> Integer.toString(values.get(wire)))
                .collect(Collectors.joining());
    }

    private Collection<String> getBinaryNumberWires(char wireId) {
        return getWires(wireId).toList();
    }

    private Stream<String> getWires(char wireId) {
        return values.keySet().stream().filter(wire -> toWireId(wire) == wireId).sorted(reverseOrder());
    }

    private static long toDecimal(String binaryNumber) {
        return Long.parseLong(binaryNumber, 2);
    }
}
