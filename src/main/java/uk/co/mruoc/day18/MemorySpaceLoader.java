package uk.co.mruoc.day18;

import java.util.Collection;
import java.util.stream.Collectors;
import lombok.Builder;
import uk.co.mruoc.Point;
import uk.co.mruoc.file.FileLoader;

@Builder
public class MemorySpaceLoader {

    private final int size;

    public MemorySpace load(String path) {
        Collection<String> lines = FileLoader.loadContentLinesFromClasspath(path);
        return toMemorySpace(lines);
    }

    private MemorySpace toMemorySpace(Collection<String> lines) {
        return new MemorySpace(size, toBytes(lines));
    }

    private Collection<Point> toBytes(Collection<String> lines) {
        return lines.stream().map(MemorySpaceLoader::toByte).toList();
    }

    private static Point toByte(String line) {
        String[] parts = line.split(",");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        return new Point(y, x);
    }
}
