package uk.co.mruoc.day24;

import static uk.co.mruoc.day24.WireConverter.toWireId;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Gates {

    private final Collection<Gate> allGates;
    private final Collection<Character> inputWireIds;
    private final char outputWireId;
    private final String lastOutBit;

    public Gates(Collection<Gate> allGates) {
        this(allGates, 'z');
    }

    public Gates(Collection<Gate> allGates, char outputWireId) {
        this(allGates, List.of('x', 'y'), outputWireId, toLastOutBit(allGates, outputWireId));
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

    public String findFaulty() {
        return allGates.stream()
                .filter(gate -> gate.isFaulty(outputWireId, lastOutBit, inputWireIds, allGates))
                .map(Gate::getOut)
                .sorted()
                .collect(Collectors.joining(","));
    }

    private static String toLastOutBit(Collection<Gate> allGates, char outputWireId) {
        return allGates.stream()
                .map(Gate::getOut)
                .filter(out -> toWireId(out) == outputWireId)
                .max(Comparator.naturalOrder())
                .orElseThrow();
    }
}
