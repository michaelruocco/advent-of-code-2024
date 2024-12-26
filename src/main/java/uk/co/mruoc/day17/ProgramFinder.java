package uk.co.mruoc.day17;

import lombok.Builder;

@Builder
public class ProgramFinder {

    private final int[] input;
    private final Registry registry;
    private final Output target;

    /*public Registry find(int[] input, UnaryOperator<Registry> registerModifier) {
            Output output = new Output();
            Registry modifiedRegistry = registerModifier.apply(registry);
            Program program = factory.build(input, registry, output);
            //program.execute();
    }
    return */
}
