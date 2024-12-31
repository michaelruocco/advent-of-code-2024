package uk.co.mruoc.day15;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.file.FileLoader;

@RequiredArgsConstructor
public class WarehouseMapLoader {

    private final WarehouseMapFactory factory;

    public WarehouseMapLoader() {
        this(new WarehouseMapFactory());
    }

    public WarehouseMap load(String path, int scale) {
        Collection<String> lines = FileLoader.loadContentLinesFromClasspath(path);
        return factory.build(toMapLines(lines), scale);
    }

    private static List<String> toMapLines(Collection<String> inputLines) {
        List<String> lines = new ArrayList<>(inputLines);
        int splitIndex = lines.indexOf("");
        return lines.subList(0, splitIndex);
    }
}
