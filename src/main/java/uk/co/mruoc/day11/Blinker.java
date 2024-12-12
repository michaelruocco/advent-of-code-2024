package uk.co.mruoc.day11;

import java.util.Collection;
import java.util.stream.LongStream;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Blinker {

    private final Rule rule;

    public Blinker() {
        this(new Rule());
    }

    public Stones blink(Stones stones, int times) {
        LongStream result = stones.stream();
        for (int i = 0; i < times; i++) {
            result = blink(result);
        }
        return new Stones(result.toArray());
    }

    public Stones blink(Stones stones) {
        return new Stones(blink(stones.stream()).toArray());
    }

    private LongStream blink(LongStream stream) {
        return stream.mapToObj(this::blink).flatMap(Collection::stream).mapToLong(l -> l);
    }

    private Collection<Long> blink(long stone) {
        return rule.apply(stone);
    }
}
