package uk.co.mruoc.day17;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Output implements Iterable<Integer> {

    private final List<Integer> values;

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

    public boolean matches(Output target) {
        if (target.values.size() > values.size()) {
            return false;
        }
        List<Integer> subList = values.subList(0, target.values.size());
        for (int i = 0; i < subList.size(); i++) {
            Integer v1 = subList.get(i);
            Integer v2 = target.values.get(i);
            if (!Objects.equals(v1, v2)) {
                return false;
            }
        }
        return true;
    }
}
