package uk.co.mruoc.day7;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Calibration {

    private final long testValue;
    private final List<Long> numbers;

    public long getFirst() {
        return numbers.get(0);
    }

    public List<Long> getRemaining() {
        return numbers.subList(1, numbers.size());
    }
}
