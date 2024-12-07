package uk.co.mruoc.day7;

import java.util.function.Supplier;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OperatorSupplier implements Supplier<Operator> {

    private final String combination;
    private final Add add;
    private final Multiply multiply;
    private final Concatenation concatenate;
    private int index;

    public OperatorSupplier(String combination) {
        this(combination, new Add(), new Multiply(), new Concatenation(), 0);
    }

    @Override
    public Operator get() {
        char c = combination.charAt(index);
        index++;
        return toOperator(c);
    }

    private Operator toOperator(char c) {
        return switch (c) {
            case '+' -> add;
            case '*' -> multiply;
            case '|' -> concatenate;
            default -> throw new UnsupportedOperationException(String.format("unsupported operator %s", c));
        };
    }
}
