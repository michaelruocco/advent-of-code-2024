package uk.co.mruoc.day17;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.With;

@Getter
@RequiredArgsConstructor
@Builder
public class Registry {

    @With
    private final long a;

    @With
    private final long b;

    @With
    private final long c;

    public Registry() {
        this(0, 0, 0);
    }

    public long toComboOperand(int literalOperand) {
        return switch (literalOperand) {
            case 0, 1, 2, 3 -> literalOperand;
            case 4 -> getA();
            case 5 -> getB();
            case 6 -> getC();
            default -> throw new InvalidComboOperandException(literalOperand);
        };
    }
}
