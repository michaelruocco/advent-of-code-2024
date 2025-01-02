package uk.co.mruoc.day24;

import java.util.Objects;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class Gate {

    private final String in1;
    private final String in2;
    private final String out;
    private final Operator operator;

    private boolean executed;

    public boolean isXOR() {
        return operator == Operator.XOR;
    }

    public void tryExecute(Wires wires) {
        Integer v1 = wires.get(in1);
        if (Objects.isNull(v1)) {
            return;
        }
        Integer v2 = wires.get(in2);
        if (Objects.isNull(v2)) {
            return;
        }
        int result = operator.apply(v1, v2);
        wires.set(out, result);
        executed = true;
    }
}
