package uk.co.mruoc.day5;

import java.util.Arrays;
import java.util.Collection;
import uk.co.mruoc.file.FileLoader;

public class PageRulesLoader {

    public PageRules loadRules(String path) {
        Collection<String> lines = FileLoader.loadContentLinesFromClasspath(path);
        return toPageRules(toRuleLines(lines));
    }

    public Pages loadPages(String path) {
        Collection<String> lines = FileLoader.loadContentLinesFromClasspath(path);
        return toPages(toPageLines(lines));
    }

    private static Collection<String> toRuleLines(Collection<String> lines) {
        return lines.stream().filter(line -> line.contains("|")).toList();
    }

    private static Collection<String> toPageLines(Collection<String> lines) {
        return lines.stream().filter(PageRulesLoader::isPageLine).toList();
    }

    private static boolean isPageLine(String line) {
        return !line.isEmpty() && !line.contains("|");
    }

    private static PageRules toPageRules(Collection<String> lines) {
        return new PageRules(lines);
    }

    private static Pages toPages(Collection<String> lines) {
        return new Pages(lines.stream().map(PageRulesLoader::toPage).toList());
    }

    private static Page toPage(String line) {
        return new Page(Arrays.stream(line.split(",")).map(Integer::parseInt).toList());
    }
}
