package uk.co.mruoc.day17;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Output implements Iterable<Integer> {

    private final Collection<Integer> values;

    public Output() {
        this(new ArrayList<>());
    }

    public void add(Integer value) {
        this.values.add(value);
    }

    public String asString() {
        return values.stream().map(i -> Integer.toString(i)).collect(Collectors.joining(","));
    }

    @Override
    public Iterator<Integer> iterator() {
        return values.iterator();
    }
}
