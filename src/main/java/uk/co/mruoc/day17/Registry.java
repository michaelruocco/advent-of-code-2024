package uk.co.mruoc.day17;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Registry {

    private static final char A = 'A';
    private static final char B = 'B';
    private static final char C = 'C';

    private final Map<Character, Integer> values;

    public Registry() {
        this(new HashMap<>());
        setA(0);
        setB(0);
        setC(0);
    }

    public int toComboOperand(int literalOperand) {
        return switch (literalOperand) {
            case 0, 1, 2, 3 -> literalOperand;
            case 4 -> getA();
            case 5 -> getB();
            case 6 -> getC();
            default -> throw new InvalidComboOperandException(literalOperand);
        };
    }

    public int getA() {
        return values.get(A);
    }

    public void setA(int value) {
        setValue(A, value);
    }

    public int getB() {
        return values.get(B);
    }

    public void setB(int value) {
        setValue(B, value);
    }

    public int getC() {
        return values.get(C);
    }

    public void setC(int value) {
        setValue(C, value);
    }

    public void setValue(char id, int value) {
        values.put(id, value);
    }
}
