package uk.co.mruoc.day15;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.file.FileLoader;

@RequiredArgsConstructor
public class WarehouseMapLoader {

    private final WarehouseMapScalePolicyFactory scalePolicyFactory;

    public WarehouseMapLoader() {
        this(new WarehouseMapScalePolicyFactory());
    }

    public WarehouseMap load(String path) {
        return load(path, 1);
    }

    public WarehouseMap load(String path, int scale) {
        Collection<String> lines = FileLoader.loadContentLinesFromClasspath(path);
        return new WarehouseMap(toMapLines(lines), toScalePolicy(scale));
    }

    private WarehouseMapScalePolicy toScalePolicy(int scale) {
        return scalePolicyFactory.build(scale);
    }

    private static List<String> toMapLines(Collection<String> inputLines) {
        List<String> lines = new ArrayList<>(inputLines);
        int splitIndex = lines.indexOf("");
        return lines.subList(0, splitIndex);
    }
}
