package uk.co.mruoc.day5;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Page implements Iterable<Integer> {

    private final List<Integer> numbers;

    public Page(Integer... numbers) {
        this(List.of(numbers));
    }

    public Page(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public Iterator<Integer> iterator() {
        return numbers.iterator();
    }

    public Stream<Integer> stream() {
        return numbers.stream();
    }

    public Collection<Integer> getPagesBefore(int number) {
        return numbers.subList(0, numbers.indexOf(number));
    }

    public Collection<Integer> getPagesAfter(int number) {
        return numbers.subList(numbers.indexOf(number) + 1, numbers.size());
    }

    public Integer getMiddleNumber() {
        return numbers.get(numbers.size() / 2);
    }
}
