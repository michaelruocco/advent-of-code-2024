package uk.co.mruoc.day17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Output implements Iterable<Long> {

    private final List<Long> values;

    public Output(int... values) {
        this(Arrays.stream(values).mapToLong(i -> i).boxed().toList());
    }

    public Output() {
        this(new ArrayList<>());
    }

    public long getFirst() {
        return values.get(0);
    }

    public Output add(long newValue) {
        List<Long> updated = new ArrayList<>(values);
        updated.add(newValue);
        return new Output(updated);
    }

    public String asString() {
        return values.stream().map(i -> Long.toString(i)).collect(Collectors.joining(","));
    }

    @Override
    public Iterator<Long> iterator() {
        return values.iterator();
    }
}
