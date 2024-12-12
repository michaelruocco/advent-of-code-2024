package uk.co.mruoc.day11;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CountingBlinker {

    private final Rule rule;

    public CountingBlinker() {
        this(new Rule());
    }

    public long blink(Stones stones, int times) {
        Map<Long, Long> counts = toCounts(stones);
        for (int i = 0; i < times; i++) {
            counts = blink(counts);
        }
        return counts.values().stream().mapToLong(l -> l).sum();
    }

    private static Map<Long, Long> toCounts(Stones stones) {
        return stones.stream().boxed().collect(Collectors.toMap(Function.identity(), value -> 1L));
    }

    private Map<Long, Long> blink(Map<Long, Long> inStones) {
        Map<Long, Long> outStones = new HashMap<>();
        for (Map.Entry<Long, Long> entry : inStones.entrySet()) {
            long stone = entry.getKey();
            long[] newStones = rule.blink(stone);
            for (long newStone : newStones) {
                long existingCount = outStones.getOrDefault(newStone, 0L);
                outStones.put(newStone, existingCount + entry.getValue());
            }
        }
        return outStones;
    }
}
