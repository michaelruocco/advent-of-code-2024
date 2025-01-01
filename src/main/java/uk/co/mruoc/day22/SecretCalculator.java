package uk.co.mruoc.day22;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SecretCalculator {

    private final int numbersInDay;
    private final Map<Long, Long> cache;

    public SecretCalculator() {
        this(2000, new HashMap<>());
    }

    public long calculateMostBananas(Collection<Long> inputs) {
        Map<String, List<Long>> allBuyerSequences = toAllBuyerSequences(inputs);
        return toHighestTotal(allBuyerSequences);
    }

    public Long calculateSumOfLastOfDay(Collection<Long> inputs) {
        return inputs.stream().mapToLong(this::calculateLastOfDay).sum();
    }

    public Long calculateLastOfDay(long input) {
        return calculateLast(input, numbersInDay);
    }

    public Long calculateLast(long input, int n) {
        long secret = input;
        for (int i = 0; i < n; i++) {
            secret = calculate(secret);
        }
        return secret;
    }

    public long[] calculateNextN(long input, int n) {
        long number = input;
        long[] secrets = new long[n + 1];
        for (int i = 0; i < n; i++) {
            secrets[i] = number;
            number = calculate(number);
        }
        secrets[n] = number;
        return secrets;
    }

    public long calculate(long input) {
        return cache.computeIfAbsent(input, key -> {
            long number = key;
            number = step1(number);
            number = step2(number);
            return step3(number);
        });
    }

    private Map<String, List<Long>> toAllBuyerSequences(Collection<Long> inputs) {
        Map<String, List<Long>> allBuyerSequences = new HashMap<>();
        for (long secretNumber : inputs) {
            Map<String, Long> buyerSequences = generateSecretNumbers(secretNumber);
            for (Map.Entry<String, Long> entry : buyerSequences.entrySet()) {
                allBuyerSequences
                        .computeIfAbsent(entry.getKey(), k -> new ArrayList<>())
                        .add(entry.getValue());
            }
        }
        return allBuyerSequences;
    }

    private Map<String, Long> generateSecretNumbers(long number) {
        long[] numbers = calculateNextN(number, numbersInDay);
        moduloAllBy10(numbers);
        String[] diffs = calculateDifferences(numbers);
        return toUniqueSequences(numbers, diffs);
    }

    private static void moduloAllBy10(long[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] %= 10;
        }
    }

    private static String[] calculateDifferences(long[] numbers) {
        String[] diffs = new String[numbers.length + 1];
        for (int i = 1; i < numbers.length; i++) {
            diffs[i] = Long.toString(numbers[i] - numbers[i - 1]);
        }
        return diffs;
    }

    private static Map<String, Long> toUniqueSequences(long[] numbers, String[] diffs) {
        Map<String, Long> sequences = new HashMap<>();
        for (int i = 1; i < diffs.length - 4; i++) {
            String sequence = toSequence(diffs, i);
            sequences.putIfAbsent(sequence, numbers[i + 3]);
        }
        return sequences;
    }

    private static String toSequence(String[] diffs, int i) {
        String[] sequence = {diffs[i], diffs[i + 1], diffs[i + 2], diffs[i + 3]};
        return String.join(",", sequence);
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

    private static long toHighestTotal(Map<String, List<Long>> allBuyerSequences) {
        long highestTotal = 0;
        for (Map.Entry<String, List<Long>> entry : allBuyerSequences.entrySet()) {
            long total = entry.getValue().stream().mapToLong(Long::longValue).sum();
            if (total > highestTotal) {
                highestTotal = total;
            }
        }
        return highestTotal;
    }
}
