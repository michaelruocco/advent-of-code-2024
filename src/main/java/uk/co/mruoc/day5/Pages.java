package uk.co.mruoc.day5;

import static java.util.function.Predicate.not;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Pages implements Iterable<Page> {

    private final Collection<Page> values;

    public Pages findCorrectlyOrdered(PageRules rules) {
        return filter(rules::isCorrectlyOrdered);
    }

    public Pages findIncorrectlyOrdered(PageRules rules) {
        return filter(not(rules::isCorrectlyOrdered));
    }

    public Pages correct(PageRules rules) {
        return new Pages(values.stream().map(page -> page.correct(rules)).toList());
    }

    private Pages filter(Predicate<Page> predicate) {
        return new Pages(values.stream().filter(predicate).toList());
    }

    public Integer getMiddlePageSum() {
        return values.stream().mapToInt(Page::getMiddleNumber).sum();
    }

    @Override
    public Iterator<Page> iterator() {
        return values.iterator();
    }
}
