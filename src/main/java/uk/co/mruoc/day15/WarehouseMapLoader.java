package uk.co.mruoc.day15;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import uk.co.mruoc.GridLoader;
import uk.co.mruoc.file.FileLoader;

public class WarehouseMapLoader {

    public WarehouseMap load(String path) {
        List<String> lines = new ArrayList<>(FileLoader.loadContentLinesFromClasspath(path));
        return toMap(GridLoader.toTokens(toMapLines(lines)));
    }

    private static List<String> toMapLines(Collection<String> inputLines) {
        List<String> lines = new ArrayList<>(inputLines);
        int splitIndex = lines.indexOf("");
        return lines.subList(0, splitIndex);
    }

    private static WarehouseMap toMap(char[][] grid) {
        return new WarehouseMap(grid);
    }
}
