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

    public ProgramState load(String path) {
        Collection<String> lines = FileLoader.loadContentLinesFromClasspath(path);
        return ProgramState.builder()
                .registry(toRegistry(toRegistryLines(lines)))
                .pointer(toPointer(lines))
                .output(new Output())
                .build();
    }

    private static List<String> toRegistryLines(Collection<String> inputLines) {
        List<String> lines = new ArrayList<>(inputLines);
        int splitIndex = lines.indexOf("");
        return lines.subList(0, splitIndex);
    }

    private static Registry toRegistry(List<String> lines) {
        return Registry.builder()
                .a(toValue(lines.get(0)))
                .b(toValue(lines.get(1)))
                .c(toValue(lines.get(2)))
                .build();
    }

    private static long toValue(String line) {
        String[] parts = line.split(" ");
        return Long.parseLong(parts[2]);
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
