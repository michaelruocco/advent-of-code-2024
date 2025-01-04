package uk.co.mruoc.day24;

import static uk.co.mruoc.day24.WireConverter.toWireId;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;
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

    public boolean isFaulty(
            char outputWireId, String lastOutBit, Collection<Character> inputWireIds, Collection<Gate> allGates) {
        if (outputsTo(outputWireId) && !outputsTo(lastOutBit)) {
            return !isXOR();
        } else if (!outputsTo(outputWireId) && !consumesFrom(inputWireIds)) {
            return isXOR();
        } else if (consumesFrom(inputWireIds) && !inputsAreFirstBit()) {
            return toOtherGates(allGates).stream().noneMatch(this::feedsInto);
        }
        return false;
    }

    private boolean isXOR() {
        return operator == Operator.XOR;
    }

    private Collection<Gate> toOtherGates(Collection<Gate> allGates) {
        return allGates.stream().filter(other -> !this.equals(other)).toList();
    }

    private boolean feedsInto(Gate other) {
        if (!other.consumesFrom(out)) {
            return false;
        }
        return other.getOperator() == getExpectedNextOperator();
    }

    private Operator getExpectedNextOperator() {
        if (operator == Operator.XOR) {
            return Operator.XOR;
        }
        return Operator.OR;
    }

    private boolean outputsTo(String wire) {
        return out.equals(wire);
    }

    private boolean outputsTo(char wireId) {
        return toWireId(out) == wireId;
    }

    private boolean consumesFrom(String wire) {
        return in1.equals(wire) || in2.equals(wire);
    }

    private boolean consumesFrom(Collection<Character> wireIds) {
        return Stream.of(in1, in2).map(WireConverter::toWireId).anyMatch(wireIds::contains);
    }

    private boolean inputsAreFirstBit() {
        return Stream.of(in1, in2).anyMatch(wire -> wire.endsWith("00"));
    }
}
