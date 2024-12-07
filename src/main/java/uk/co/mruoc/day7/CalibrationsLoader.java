package uk.co.mruoc.day7;

import static uk.co.mruoc.file.FileLoader.loadContentLinesFromClasspath;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CalibrationsLoader {

    private final Collection<Character> operators;

    public CalibrationsLoader() {
        this(List.of('+', '*'));
    }

    public Calibrations load(String path) {
        return new Calibrations(loadContentLinesFromClasspath(path).stream()
                .map(this::toCalibration)
                .toList());
    }

    public Calibration toCalibration(String line) {
        String[] parts = line.split(":");
        long testValue = Long.parseLong(parts[0]);
        List<Long> numbers = toNumbers(parts[1]);
        return new Calibration(testValue, numbers, operators);
    }

    private static List<Long> toNumbers(String input) {
        return Arrays.stream(input.trim().split(" ")).map(Long::parseLong).toList();
    }
}
