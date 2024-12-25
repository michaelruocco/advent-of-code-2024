package uk.co.mruoc.day17;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Registers {

    private int a;
    private int b;
    private int c;

    public int toValue(int operand) {
        return switch (operand) {
            case 4 -> a;
            case 5 -> b;
            case 6 -> c;
            default -> operand;
        };
    }
}
