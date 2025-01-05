package uk.co.mruoc.day7;

import java.util.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CalibrationCalculator {

    private final Collection<Operator> operators;

    public long calculate(Collection<Calibration> calibrations) {
        return filter(calibrations).stream().mapToLong(Long::longValue).sum();
    }

    private List<Long> filter(Collection<Calibration> calibrations) {
        return calibrations.stream()
                .filter(calibration ->
                        check(calibration.getTestValue(), calibration.getFirst(), calibration.getRemaining()))
                .map(Calibration::getTestValue)
                .toList();
    }

    private boolean check(long target, long acc, List<Long> nums) {
        if (acc > target) {
            return false;
        }
        if (nums.isEmpty()) {
            return target == acc;
        }
        long num = nums.get(0);
        List<Long> rest = nums.subList(1, nums.size());
        return operators.stream()
                .map(operator -> operator.apply(acc, num))
                .map(newAcc -> check(target, newAcc, rest))
                .filter(result -> result)
                .findFirst()
                .orElse(false);
    }
}
