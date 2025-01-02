package uk.co.mruoc.day23;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import uk.co.mruoc.file.FileLoader;

public class ComputerLoader {

    public Map<String, Collection<String>> load(String path) {
        Collection<String> lines = FileLoader.loadContentLinesFromClasspath(path);
        return toConnectedComputers(lines);
    }

    private Map<String, Collection<String>> toConnectedComputers(Collection<String> lines) {
        Map<String, Collection<String>> map = new HashMap<>();
        for (String line : lines) {
            int dashIdx = line.indexOf('-');
            String first = line.substring(0, dashIdx);
            String second = line.substring(dashIdx + 1);
            map.computeIfAbsent(first, k -> new HashSet<>()).add(second);
            map.computeIfAbsent(second, k -> new HashSet<>()).add(first);
        }
        return map;
    }
}
