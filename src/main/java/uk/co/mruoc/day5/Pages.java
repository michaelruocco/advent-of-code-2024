package uk.co.mruoc.day5;

import java.util.Collection;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Pages {

    private final Collection<Page> values;

    public Pages findCorrectlyOrdered(PageRules rules) {
        return new Pages(values.stream().filter(rules::isCorrectlyOrdered).toList());
    }

    public Integer getMiddlePageSum() {
        return values.stream().mapToInt(Page::getMiddleNumber).sum();
    }
}
