package uk.co.mruoc.day17;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Instructions {

    public Map<Integer, Instruction> asMap() {
        return build().stream().collect(Collectors.toMap(Instruction::getOpCode, Function.identity()));
    }

    private Collection<Instruction> build() {
        return List.of(
                new AdvInstruction(), // op code 0
                new BxlInstruction(), // op code 1
                new BstInstruction(), // op code 2
                new JnzInstruction(), // op code 3
                new BxcInstruction(), // op code 4
                new OutInstruction(), // op code 5
                new BdvInstruction(), // op code 6
                new CdvInstruction() // op code 7
                );
    }
}
