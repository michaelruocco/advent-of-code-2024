package uk.co.mruoc.day17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.file.FileLoader;

@RequiredArgsConstructor
public class ProgramLoader {

    private final Output output;
    private final ProgramFactory factory;

    public ProgramLoader(Output output) {
        this(output, new ProgramFactory());
    }

    public Program load(String path) {
        Collection<String> lines = FileLoader.loadContentLinesFromClasspath(path);
        Registers registers = toRegisters(toRegistryLines(lines));
        int[] input = toProgramLine(lines).map(ProgramLoader::toInput).orElseThrow();
        return factory.build(input, registers, output);
    }

    private static List<String> toRegistryLines(Collection<String> inputLines) {
        List<String> lines = new ArrayList<>(inputLines);
        int splitIndex = lines.indexOf("");
        return lines.subList(0, splitIndex);
    }

    private static Registers toRegisters(Collection<String> lines) {
        Registers registers = new Registers();
        lines.forEach(line -> populate(registers, line));
        return registers;
    }

    private static void populate(Registers registers, String line) {
        String[] parts = line.split(" ");
        char id = parts[1].charAt(0);
        int value = Integer.parseInt(parts[2]);
        registers.setValue(id, value);
    }

    private static Optional<String> toProgramLine(Collection<String> inputLines) {
        List<String> lines = new ArrayList<>(inputLines);
        long splitIndex = lines.indexOf("");
        return lines.stream().skip(splitIndex + 1).findFirst();
    }

    private static int[] toInput(String line) {
        return Arrays.stream(line.split(" ")[1].split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
