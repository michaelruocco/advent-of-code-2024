package uk.co.mruoc.day24;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uk.co.mruoc.file.FileLoader;

public class WiredGatesLoader {

    public Wires loadWires(String path) {
        Collection<String> lines = FileLoader.loadContentLinesFromClasspath(path);
        return toWires(toWiresLines(lines));
    }

    public Gates loadGates(String path) {
        Collection<String> lines = FileLoader.loadContentLinesFromClasspath(path);
        return toGates(toGatesLines(lines));
    }

    private static Collection<String> toWiresLines(Collection<String> lines) {
        long splitIndex = toSplitIndex(lines);
        return lines.stream().limit(splitIndex).toList();
    }

    private static Wires toWires(Collection<String> lines) {
        Map<String, Integer> values = new HashMap<>();
        lines.stream()
                .map(line -> line.split(":"))
                .forEach(parts -> values.put(parts[0], Integer.parseInt(parts[1].trim())));
        return new Wires(values);
    }

    private Gates toGates(Collection<String> lines) {
        return new Gates(lines.stream().map(this::toGate).toList());
    }

    private Gate toGate(String line) {
        String[] parts = line.split("->");
        String out = parts[1].trim();

        String[] subParts = parts[0].split(" ");
        String in1 = subParts[0];
        String operator = subParts[1];
        String in2 = subParts[2];
        return new Gate(in1, in2, out, Operator.valueOf(operator));
    }

    private static Collection<String> toGatesLines(Collection<String> lines) {
        long splitIndex = toSplitIndex(lines);
        return lines.stream().skip(splitIndex + 1).toList();
    }

    private static long toSplitIndex(Collection<String> inputLines) {
        List<String> lines = new ArrayList<>(inputLines);
        return lines.indexOf("");
    }
}
