package uk.co.mruoc.day2;

import java.util.Collection;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Reports {

    private final Collection<Report> values;

    public long getSafeCount() {
        return values.stream().filter(Report::isSafe).count();
    }
}
