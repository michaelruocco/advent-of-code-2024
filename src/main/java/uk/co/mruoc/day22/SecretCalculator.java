package uk.co.mruoc.day22;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
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

    public long calculateMostBananas1(Collection<Long> inputs) {
        // Store sequences for all buyers
        Map<String, List<Long>> sequencesForAllBuyers = new HashMap<>();

        for (long secretNumber : inputs) {
            Map<String, Long> sequencesForBuyer = generateSecretNumbers(secretNumber, 2000);

            // Add sequences to the overall map
            for (Map.Entry<String, Long> entry : sequencesForBuyer.entrySet()) {
                String sequence = entry.getKey();
                long bananas = entry.getValue();
                sequencesForAllBuyers
                        .computeIfAbsent(sequence, k -> new ArrayList<>())
                        .add(bananas);
            }
        }

        long bestTotal = 0;
        for (Map.Entry<String, List<Long>> entry : sequencesForAllBuyers.entrySet()) {
            List<Long> bananasFromAllBuyers = entry.getValue();
            long total =
                    bananasFromAllBuyers.stream().mapToLong(Long::longValue).sum();
            if (total > bestTotal) {
                bestTotal = total;
            }
        }
        return bestTotal;
    }

    public Map<String, Long> generateSecretNumbers(long secretNumber, int n) {
        Map<String, Long> sequences = new HashMap<>();
        long[] secretNumbers = new long[n + 1];

        // Generate the sequence of secret numbers
        for (int i = 0; i < n; i++) {
            secretNumbers[i] = secretNumber;
            secretNumber = calculate(secretNumber);
        }
        secretNumbers[n] = secretNumber;

        // Reduce each secret number mod 10
        for (int i = 0; i < secretNumbers.length; i++) {
            secretNumbers[i] %= 10;
        }

        String[] diffs = new String[n + 1];
        // Calculate differences
        for (int i = 1; i < secretNumbers.length; i++) {
            diffs[i] = Long.toString(secretNumbers[i] - secretNumbers[i - 1]);
        }

        // Store unique sequences
        for (int i = 1; i < diffs.length - 4; i++) {
            String[] sequence = {diffs[i], diffs[i + 1], diffs[i + 2], diffs[i + 3]};
            String key = String.join(",", sequence);
            if (!sequences.containsKey(key)) {
                sequences.put(key, secretNumbers[i + 3]);
            }
        }

        return sequences;
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
        return cache.computeIfAbsent(input, key -> {
            long number = key;
            number = step1(number);
            number = step2(number);
            return step3(number);
        });
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
