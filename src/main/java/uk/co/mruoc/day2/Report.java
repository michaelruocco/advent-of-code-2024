package uk.co.mruoc.day2;

import java.util.Collection;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Report {

    private final Collection<Integer> levels;

    public boolean isSafe() {
        return SafetyCalculator.isSafe(levels);
    }
}
