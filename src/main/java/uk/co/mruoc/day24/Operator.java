package uk.co.mruoc.day24;

import java.util.function.BiFunction;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Operator {
    AND(Operator::and),
    OR(Operator::or),
    XOR(Operator::xor);

    private final BiFunction<Integer, Integer, Integer> function;

    public int apply(int in1, int in2) {
        return function.apply(in1, in2);
    }

    private static Integer and(Integer in1, Integer in2) {
        if (in1 == 1 && in2 == 1) {
            return 1;
        }
        return 0;
    }

    private static Integer or(Integer in1, Integer in2) {
        if (in1 == 1 || in2 == 1) {
            return 1;
        }
        return 0;
    }

    private static Integer xor(Integer in1, Integer in2) {
        if (!in1.equals(in2)) {
            return 1;
        }
        return 0;
    }
}
