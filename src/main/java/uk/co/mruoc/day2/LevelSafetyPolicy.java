package uk.co.mruoc.day2;

import java.util.Collection;

public interface LevelSafetyPolicy {

    boolean isSafe(Collection<Integer> levels);
}
