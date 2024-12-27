package uk.co.mruoc.day17;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import lombok.Builder;

@Builder
public class ProgramFinder {

    private final Program program;

    public long find(final ProgramState input) {
        Collection<Long> candidateAs = new HashSet<>(Set.of(0L));
        for (int i = 1; i <= input.getProgramLength(); i++) {
            Collection<Long> nextCandidateAs = new HashSet<>();
            for (long previousA : candidateAs) {
                for (int j = 0; j <= 7; j++) {
                    long a = previousA * 8 + j;
                    ProgramState state = program.execute(input.setA(a));
                    int index = input.getProgramLength() - i;
                    if (state.getFirstOutput() == input.getProgramValueAtIndex(index)) {
                        nextCandidateAs.add(a);
                    }
                }
            }
            candidateAs = nextCandidateAs;
        }
        return candidateAs.stream()
                .filter(a -> program.execute(input.setA(a)).outputAsString().equals(input.programAsString()))
                .mapToLong(i -> i)
                .min()
                .orElseThrow();
    }
}
