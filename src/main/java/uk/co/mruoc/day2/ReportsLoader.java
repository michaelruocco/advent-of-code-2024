package uk.co.mruoc.day2;

import java.util.Arrays;
import java.util.Collection;
import uk.co.mruoc.file.FileLoader;

public class ReportsLoader {

    public Reports load(String path) {
        Collection<String> lines = FileLoader.loadContentLinesFromClasspath(path);
        return toReports(lines);
    }

    private static Reports toReports(Collection<String> lines) {
        return new Reports(lines.stream().map(ReportsLoader::toReport).toList());
    }

    private static Report toReport(String line) {
        Collection<Integer> levels = Arrays.stream(line.split(" "))
                .mapToInt(Integer::parseInt)
                .boxed()
                .toList();
        return new Report(levels);
    }
}
