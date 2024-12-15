package uk.co.mruoc.day13;

import java.util.Collection;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClawMachines {

    private final Collection<ClawMachine> machines;

    public long calculateCost() {
        return machines.stream()
                .map(ClawMachine::calculateCost)
                .flatMap(Optional::stream)
                .mapToLong(i -> i)
                .sum();
    }
}
