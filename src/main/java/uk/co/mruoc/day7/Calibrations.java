package uk.co.mruoc.day7;

import java.util.Collection;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Calibrations {

    private final Collection<Calibration> values;

    public long getTotalResult() {
        return values.stream()
                .filter(Calibration::couldBeTrue)
                .mapToLong(Calibration::getTestValue)
                .sum();
    }
}
