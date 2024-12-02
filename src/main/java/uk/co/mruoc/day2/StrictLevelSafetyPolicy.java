package uk.co.mruoc.day2;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.BiPredicate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StrictLevelSafetyPolicy implements LevelSafetyPolicy {

    @Override
    public boolean isSafe(Collection<Integer> levels) {
        if (allMatch(levels, safeDifference())) {
            return allMatch(levels, ascending()) || allMatch(levels, descending());
        }
        return false;
    }

    private static boolean allMatch(Collection<Integer> values, BiPredicate<Integer, Integer> safetyCheck) {
        if (values.isEmpty() || values.size() == 1) {
            return true;
        }

        Iterator<Integer> iterator = values.iterator();
        int current = iterator.next();
        int previous = current;
        while (iterator.hasNext()) {
            current = iterator.next();
            if (!safetyCheck.test(previous, current)) {
                return false;
            }
            previous = current;
        }
        return true;
    }

    private static BiPredicate<Integer, Integer> safeDifference() {
        return (previous, current) -> {
            int difference = toDifference(previous, current);
            return difference >= 1 && difference <= 3;
        };
    }

    private static BiPredicate<Integer, Integer> ascending() {
        return (previous, current) -> previous.compareTo(current) > 0;
    }

    private static BiPredicate<Integer, Integer> descending() {
        return (previous, current) -> previous.compareTo(current) < 0;
    }

    private static Integer toDifference(int previous, int current) {
        return Math.abs(previous - current);
    }
}
