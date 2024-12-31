package uk.co.mruoc.day22;

import java.util.Collection;
import java.util.LinkedList;

public class SecretCalculator {

    public Collection<Long> calculateNextN(long input, int n) {
        LinkedList<Long> secrets = new LinkedList<>();
        secrets.add(input);
        for (int i = 0; i < n; i++) {
            secrets.add(calculate(secrets.getLast()));
        }
        secrets.removeFirst();
        return secrets;
    }

    public long calculate(long input) {
        long number = input;
        number = step1(number);
        number = step2(number);
        return step3(number);
    }

    private static long step1(long i) {
        return prune(mix(i, i * 64));
    }

    private static long step2(long i) {
        return mix(i, i / 32);
    }

    private static long step3(long i) {
        return prune(mix(i, i * 2048));
    }

    private static long mix(long a, long b) {
        return a ^ b;
    }

    private static long prune(long i) {
        return i % 16777216;
    }
}
