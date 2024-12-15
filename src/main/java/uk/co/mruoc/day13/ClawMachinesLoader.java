package uk.co.mruoc.day13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.UnaryOperator;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import uk.co.mruoc.file.FileLoader;

@RequiredArgsConstructor
public class ClawMachinesLoader {

    private final UnaryOperator<Prize> prizeTransformer;

    public ClawMachinesLoader() {
        this(p -> p);
    }

    public ClawMachines load(String path) {
        Collection<String> lines = FileLoader.loadContentLinesFromClasspath(path);
        return toClawMachines(new ArrayList<>(lines));
    }

    private ClawMachines toClawMachines(Collection<String> lines) {
        List<String> nonEmptyLines = removeNonEmpty(lines);
        return new ClawMachines(ListUtils.partition(nonEmptyLines, 3).stream()
                .map(this::toClawMachine)
                .toList());
    }

    private ClawMachine toClawMachine(List<String> lines) {
        return ClawMachine.builder()
                .a(toButton(lines.get(0)))
                .b(toButton(lines.get(1)))
                .prize(prizeTransformer.apply(toPrize(lines.get(2))))
                .build();
    }

    private static List<String> removeNonEmpty(Collection<String> lines) {
        return lines.stream().filter(line -> !line.isEmpty()).toList();
    }

    private static Button toButton(String s) {
        String[] parts = splitOnColon(s);
        long[] longs = toLongs(parts[1], "\\+");
        return new Button(toButtonId(parts[0]), longs[0], longs[1]);
    }

    private static String toButtonId(String s) {
        return s.split(" ")[1];
    }

    private static Prize toPrize(String s) {
        String[] parts = splitOnColon(s);
        long[] longs = toLongs(parts[1], "=");
        return new Prize(longs[0], longs[1]);
    }

    private static String[] splitOnColon(String s) {
        return s.split(":");
    }

    private static long[] toLongs(String s, String delimiter) {
        return Arrays.stream(s.split(","))
                .mapToLong(part -> toLong(part, delimiter))
                .toArray();
    }

    private static long toLong(String s, String delimiter) {
        String[] parts = s.split(delimiter);
        return Long.parseLong(parts[1].trim());
    }
}
