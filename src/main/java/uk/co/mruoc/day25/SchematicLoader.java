package uk.co.mruoc.day25;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import uk.co.mruoc.file.FileLoader;

public class SchematicLoader {

    public Collection<Schematic> load(String path) {
        Collection<String> lines = FileLoader.loadContentLinesFromClasspath(path);
        return toSchematics(lines);
    }

    private static Collection<Schematic> toSchematics(Collection<String> lines) {
        Collection<Schematic> schematics = new ArrayList<>();
        List<String> schematic = new ArrayList<>();
        lines.forEach(line -> process(line, schematic, schematics));
        if (!schematic.isEmpty()) {
            schematics.add(toSchematic(schematic));
        }
        return schematics;
    }

    private static void process(String line, Collection<String> schematic, Collection<Schematic> schematics) {
        if (line.isEmpty()) {
            if (!schematic.isEmpty()) {
                schematics.add(toSchematic(schematic));
                schematic.clear();
            }
        } else {
            schematic.add(line);
        }
    }

    private static Schematic toSchematic(Collection<String> lines) {
        return new Schematic(new ArrayList<>(lines));
    }
}
