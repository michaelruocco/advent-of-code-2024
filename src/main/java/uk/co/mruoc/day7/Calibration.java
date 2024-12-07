package uk.co.mruoc.day7;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import lombok.Getter;

public class Calibration {

    @Getter
    private final long testValue;

    private final List<Long> numbers;
    private final Collection<OperatorSupplier> operatorSuppliers;

    public Calibration(long testValue, List<Long> numbers, Collection<Character> operators) {
        this.testValue = testValue;
        this.numbers = numbers;
        this.operatorSuppliers = toOperatorSuppliers(numbers.size(), operators);
    }

    public boolean couldBeTrue() {
        return operatorSuppliers.stream().anyMatch(this::couldBeTrue);
    }

    private boolean couldBeTrue(Supplier<Operator> supplier) {
        long result = 0;
        long n1 = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            long n2 = numbers.get(i);
            Operator operator = supplier.get();
            result = operator.apply(n1, n2);
            if (result > testValue) {
                return false;
            }
            n1 = result;
        }
        return result == testValue;
    }

    private Collection<OperatorSupplier> toOperatorSuppliers(int length, Collection<Character> operators) {
        Collection<String> combinations = generateCombinations(length - 1, operators);
        return combinations.stream().map(OperatorSupplier::new).toList();
    }

    private Collection<String> generateCombinations(int length, Collection<Character> operators) {
        if (length == 0) {
            return Collections.singletonList("");
        }
        Collection<String> shorterCombinations = generateCombinations(length - 1, operators);
        Collection<String> combinations = new ArrayList<>();
        for (String combination : shorterCombinations) {
            operators.forEach(operator -> combinations.add(combination + operator));
        }
        return combinations;
    }
}
