package uk.co.mruoc.day2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DampenedLevelSafetyPolicy implements LevelSafetyPolicy {

    private final LevelSafetyPolicy policy;

    public DampenedLevelSafetyPolicy() {
        this(new StrictLevelSafetyPolicy());
    }

    public boolean isSafe(Collection<Integer> levels) {
        if (levels.isEmpty()) {
            return true;
        }
        return dampen(levels).anyMatch(policy::isSafe);
    }

    private Stream<Collection<Integer>> dampen(Collection<Integer> levels) {
        return IntStream.range(0, levels.size()).mapToObj(i -> remove(levels, i));
    }

    private static Collection<Integer> remove(Collection<Integer> levels, int index) {
        List<Integer> copy = new ArrayList<>(levels);
        copy.remove(index);
        return copy;
    }
}
