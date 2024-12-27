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
                new AdvInstruction(),
                new BxlInstruction(),
                new BstInstruction(),
                new JnzInstruction(),
                new BxcInstruction(),
                new OutInstruction(),
                new BdvInstruction(),
                new CdvInstruction());
    }
}
