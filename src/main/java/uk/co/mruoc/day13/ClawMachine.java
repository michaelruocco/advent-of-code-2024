package uk.co.mruoc.day13;

import java.util.Optional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
public class ClawMachine {

    private final Button a;
    private final Button b;
    private final Prize prize;

    public Optional<Long> calculateCost() {
        return checkCombination(a.x, a.y, b.x, b.y, prize.x, prize.y).map(ClawMachine::toCost);
    }

    private static Optional<Combination> checkCombination(long ax, long ay, long bx, long by, long px, long py) {
        long numeratorX = px * by - py * bx;
        long denominatorX = ax * by - ay * bx;

        long numeratorY = px * ay - py * ax;
        long denominatorY = ay * bx - ax * by;

        if (numeratorX % denominatorX == 0 && numeratorY % denominatorY == 0) {
            long a = numeratorX / denominatorX;
            long b = numeratorY / denominatorY;
            return Optional.of(new Combination(a, b));
        }
        return Optional.empty();
    }

    private static long toCost(Combination combination) {
        return (combination.a * 3) + combination.b;
    }

    @RequiredArgsConstructor
    private static class Combination {
        final long a;
        final long b;
    }
}
