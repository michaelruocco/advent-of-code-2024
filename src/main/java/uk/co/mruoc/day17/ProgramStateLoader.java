package uk.co.mruoc.day17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.file.FileLoader;

@RequiredArgsConstructor
public class ProgramStateLoader {

    private final Output output;

    public ProgramState load(String path) {
        Collection<String> lines = FileLoader.loadContentLinesFromClasspath(path);
        return ProgramState.builder()
                .registry(toRegistry(toRegistryLines(lines)))
                .pointer(toPointer(lines))
                .output(output)
                .build();
    }

    private static List<String> toRegistryLines(Collection<String> inputLines) {
        List<String> lines = new ArrayList<>(inputLines);
        int splitIndex = lines.indexOf("");
        return lines.subList(0, splitIndex);
    }

    private static Registry toRegistry(Collection<String> lines) {
        Registry registry = new Registry();
        lines.forEach(line -> populate(registry, line));
        return registry;
    }

    private static void populate(Registry registry, String line) {
        String[] parts = line.split(" ");
        char id = parts[1].charAt(0);
        int value = Integer.parseInt(parts[2]);
        registry.setValue(id, value);
    }

    private static Pointer toPointer(Collection<String> lines) {
        return toProgramLine(lines)
                .map(ProgramStateLoader::toInput)
                .map(Pointer::new)
                .orElseThrow();
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
