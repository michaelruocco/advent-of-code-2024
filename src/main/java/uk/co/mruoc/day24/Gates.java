package uk.co.mruoc.day24;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Gates {

    private final Collection<Gate> allGates;

    public String findFaulty() {
        Gate lastZGate = allGates.stream()
                .filter(g -> g.getOut().startsWith("z"))
                .max(Comparator.comparing(Gate::getOut))
                .orElseThrow();

        Set<Gate> faultyGates = new HashSet<>();
        for (Gate gate : allGates) {
            boolean isFaulty = false;

            if (gate.getOut().startsWith("z") && !gate.getOut().equals(lastZGate.getOut())) {
                isFaulty = !gate.isXOR();
            } else if (!gate.getOut().startsWith("z") && !isInputWire(gate.getIn1()) && !isInputWire(gate.getIn2())) {
                isFaulty = gate.isXOR();
            } else if (isInputWire(gate.getIn1())
                    && isInputWire(gate.getIn2())
                    && !areInputsFirstBit(gate.getIn1(), gate.getIn2())) {
                String output = gate.getOut();
                Operator expectedNextOperator = gate.getOperator() == Operator.XOR ? Operator.XOR : Operator.OR;

                boolean feedsIntoExpectedGate = allGates.stream()
                        .anyMatch(other -> !other.equals(gate)
                                && (other.getIn1().equals(output)
                                        || other.getIn2().equals(output))
                                && other.getOperator() == expectedNextOperator);

                isFaulty = !feedsIntoExpectedGate;
            }

            if (isFaulty) {
                faultyGates.add(gate);
            }
        }

        return faultyGates.stream().map(Gate::getOut).sorted().collect(Collectors.joining(","));
    }

    private static boolean isInputWire(String wire) {
        return wire.charAt(0) == 'x' || wire.charAt(0) == 'y';
    }

    private static boolean areInputsFirstBit(String in1, String in2) {
        return in1.endsWith("00") && in2.endsWith("00");
    }

    public void execute(Wires wires) {
        List<Gate> remainingGates = new ArrayList<>(allGates);
        int index = 0;
        while (!remainingGates.isEmpty()) {
            Gate gate = remainingGates.get(index);
            gate.tryExecute(wires);
            if (gate.isExecuted()) {
                index = 0;
                remainingGates.remove(gate);
            }
            index++;
            if (index > remainingGates.size() - 1) {
                index = 0;
            }
        }
    }
}
