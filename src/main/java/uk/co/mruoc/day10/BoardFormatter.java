package uk.co.mruoc.day10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardFormatter {

    public static String toString(TrailMap map, List<Move> route) {
        char[][] m = new char[map.size()][map.size()];
        for (int i = 0; i < route.size(); i++) {
            Move move = route.get(i);
            if (i == 0) {
                Point from = move.from;
                m[from.y][from.x] = toChar(map.getHeight(from));
            }
            Point to = move.to;
            m[to.y][to.x] = toChar(map.getHeight(to));
        }
        Collection<String> lines = new ArrayList<>();
        for (char[] chars : m) {
            StringBuilder row = new StringBuilder();
            for (int x = 0; x < m.length; x++) {
                row.append(chars[x]);
            }
            lines.add(row.toString());
        }
        return lines.stream().collect(Collectors.joining(System.lineSeparator()));
    }

    private static char toChar(int i) {
        return (char) (i + '0');
    }
}
