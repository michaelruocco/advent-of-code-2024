package uk.co.mruoc.day25;

import java.util.Collections;
import java.util.List;
import lombok.Getter;

@Getter
public class Schematic {

    private final boolean lock;
    private final int[] heights;
    private final int maxHeight;

    public Schematic(List<String> lines) {
        lock = isForLock(toFirstLine(lines));
        heights = toHeights(lock ? lines : reverse(lines));
        maxHeight = lines.size() - 1;
    }

    public boolean isLock() {
        return lock;
    }

    public boolean accepts(Schematic other) {
        for (int i = 0; i < heights.length; i++) {
            int total = heights[i] + other.heights[i];
            if (total >= maxHeight) {
                return false;
            }
        }
        return true;
    }

    private static List<String> reverse(List<String> input) {
        List<String> copy = input.subList(0, input.size());
        Collections.reverse(copy);
        return copy;
    }

    private static int[] toHeights(List<String> lines) {
        int[] heights = new int[lines.get(0).length()];
        for (int y = lines.size() - 1; y > 0; y--) {
            char[] line = lines.get(y).toCharArray();
            for (int x = 0; x < line.length; x++) {
                char c = line[x];
                if (heights[x] == 0 && c == '#') {
                    heights[x] = y;
                }
            }
        }
        return heights;
    }

    private static boolean isForLock(String firstLine) {
        return containsOnly(firstLine, '#');
    }

    private static String toFirstLine(List<String> lines) {
        return lines.stream().findFirst().orElseThrow();
    }

    private static boolean containsOnly(String input, char target) {
        for (char c : input.toCharArray()) {
            if (c != target) {
                return false;
            }
        }
        return true;
    }
}
