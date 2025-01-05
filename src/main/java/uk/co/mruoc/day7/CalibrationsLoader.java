package uk.co.mruoc.day7;

import static uk.co.mruoc.file.FileLoader.loadContentLinesFromClasspath;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CalibrationsLoader {

    public Collection<Calibration> load(String path) {
        return loadContentLinesFromClasspath(path).stream()
                .map(this::toCalibration)
                .toList();
    }

    public Calibration toCalibration(String line) {
        String[] parts = line.split(":");
        return Calibration.builder()
                .testValue(Long.parseLong(parts[0]))
                .numbers(toNumbers(parts[1]))
                .build();
    }

    private static List<Long> toNumbers(String input) {
        return Arrays.stream(input.trim().split(" ")).map(Long::parseLong).toList();
    }
}
