package uk.co.mruoc.day11;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Stones {

    private final long[] longs;

    public Stones(String input) {
        this(toLongs(input));
    }

    public LongStream stream() {
        return Arrays.stream(longs);
    }

    public String asString() {
        return stream().mapToObj(Long::toString).collect(Collectors.joining(" "));
    }

    private static long[] toLongs(String input) {
        return Arrays.stream(input.split(" ")).mapToLong(Long::parseLong).toArray();
    }
}
